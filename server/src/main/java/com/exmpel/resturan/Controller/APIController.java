package com.exmpel.resturan.Controller;


import com.exmpel.resturan.Entity.*;
import com.exmpel.resturan.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private ResturanRepository resturanRep;

    private searchModel seModel;

    @PostMapping("/search")
    public searchModel setSerchMode(@ModelAttribute searchModel se)
    {
        this.seModel=se;
        return this.seModel;
    }
    @GetMapping("/search")
    public searchModel setSerchMode()
    {
        return this.seModel;
    }

    @PostMapping("/restaurant")
    public List<ResponseResturnModel> allResturan(@RequestParam String city,@RequestParam String area,@RequestParam(required = false) List<String> category)
    {
        ArrayList<ResponseResturnModel> list=new ArrayList<ResponseResturnModel>();
        for (Resturan x:resturanRep.findAll()) {
            if (x.getAddress()!=null&&x.getAddress().getArea().equals(area) && x.getAddress().getCity().equals(city))
                if (category != null) {
                    boolean f=false;
                    for (Resturan_Catagory category1 : x.getCategories()) {
                        for (String cat : category)
                            if (cat.equals(category1.getCategory().getName())) {
                                f=true;
                                break;
                            }
                    }
                    if (f)
                        list.add(this.ResturanMap(x));
                } else
                    list.add(this.ResturanMap(x));
        }
        list.sort(new Comparator<ResponseResturnModel>() {
            @Override
            public int compare(ResponseResturnModel o1, ResponseResturnModel o2) {
                return o2.getOpen().compareTo(o1.getOpen());
            }
        });
        return list;
    }


    @GetMapping("/restaurant/{id}")
    public Resturan get(@PathVariable Long id)
    {
        return resturanRep.findById(id).get();
    }


    @GetMapping("/restaurant/{id}/comments")
    public List<Comment> getcomment(@PathVariable Long id)
    {
        return resturanRep.findById(id).get().getComments();
    }

    @Autowired
    private CommentRepository comRep;

    @PostMapping("/restaurant/{id}/comments")
    public Comment addcomment(@PathVariable Long id,@ModelAttribute CommentModel comnt)
    {
        Comment com=new Comment();
        com.setCreated_at(new Date());
        com.setAuthor(comnt.getAuthor());
        com.setDeliveryTime(comnt.getDeleverytime());
        com.setQuality(comnt.getRate());
//        com.setPackaging(2);
        com.setText(comnt.getText());
        Resturan res=resturanRep.findById(id).get();
        com.setResturan(res);
        if(!res.getAverageRate().equals(Double.valueOf(0))) {

            Double avg = ((res.getAverageRate() * res.getComments().size()) + comnt.getRate()) / (res.getComments().size() + 1);
            res.setAverageRate(avg);
        }
        else {
            res.setAverageRate(Double.valueOf(comnt.getRate()));
        }
        resturanRep.save(res);
        return comRep.save(com);
    }

    @Autowired
    private AddresRepository adRep;

    @PostMapping("restaurant/add")
    public Resturan add(@ModelAttribute ResturanModel res)
    {

        Resturan rest=new Resturan();
        rest.setName(res.getName());
        rest.setAverageRate((double) 0);
        rest.setOpeningTime(res.getOpeningTime());
        rest.setClosingTime(res.getClosingTime());
        rest=resturanRep.save(rest);
        rest.setLogo("/restaurant/"+rest.getId()+"/logo");
        rest=resturanRep.save(rest);
        String fileName = StringUtils.cleanPath(res.getLogo().getOriginalFilename());
        Logo dbFile = null;

        try {
            dbFile= new Logo(fileName, res.getLogo().getContentType(), res.getLogo().getBytes());
            dbFile.setResturan(rest);

        } catch (IOException e) {
            e.printStackTrace();
        }
        logRep.save(dbFile);
        Addres ad=new Addres();
        ad.setArea(res.getArea());
        ad.setCity(res.getCity());
        ad.setAddressLine(res.getAddressLine());
        ad.setResturan(rest);
        adRep.save(ad);
        rest.setAddress(ad);
        return rest;
    }

    @Autowired
    private LogoRepository logRep;

    @GetMapping("/restaurant/{fileId}/logo")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database

        Logo dbFile = logRep.findByResturan(this.resturanRep.findById(Long.valueOf(fileId)).get());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(convert(dbFile.getData())));
    }

    @Autowired
    private CategoryRepository catRep;

    @GetMapping("/category")
    public List<Category> all()
    {
        return catRep.findAll();
    }

    public ResponseResturnModel ResturanMap(Resturan resturan)
    {
        ResponseResturnModel res=new ResponseResturnModel();
        res.setId(resturan.getId());
        res.setLogo(resturan.getLogo());
        res.setAddress(resturan.getAddress());
        res.setAverageRate(resturan.getAverageRate());
        res.setClosingTime(resturan.getClosingTime());
        res.setOpeningTime(resturan.getOpeningTime());
        res.setName(resturan.getName());
        res.setFoods(resturan.getFoods());
        res.setCategories(resturan.getCategories());
        res.setComments(resturan.getComments());
        java.util.Date date=new java.util.Date();
        if (Integer.parseInt(resturan.getOpeningTime())<date.getHours()&&Integer.parseInt(resturan.getClosingTime())>date.getHours())
            res.setOpen(true);
        else
            res.setOpen(false);


        return res;

    }

    public byte[] convert(Byte[] byteObjects)
    {
        byte[] bytes=new byte[byteObjects.length];
        int j=0;
        for(Byte b: byteObjects)
            bytes[j++] = b.byteValue();
        return bytes;
    }
}
