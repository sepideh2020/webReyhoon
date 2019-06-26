package com.exmpel.resturan.Controller;


import com.exmpel.resturan.Entity.*;
import com.exmpel.resturan.Service.AddresRepository;
import com.exmpel.resturan.Service.CommentRepository;
import com.exmpel.resturan.Service.LogoRepository;
import com.exmpel.resturan.Service.ResturanRepository;
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
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost", maxAge = 8080)
@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private ResturanRepository resturanRep;

    @GetMapping("/restaurant")
    public List<Resturan> allResturan(@RequestParam String area,@RequestParam(required = false) List<String> category)
    {
        ArrayList<Resturan> list=new ArrayList<Resturan>();
        for (Resturan x:resturanRep.findAll())
            if(x.getAddress().getArea().equals(area))
                if(category!=null) {
                    for (String cat : category)
                        for (Resturan_Catagory category1 : x.getCategories())
                            if (cat.equals(category1.getCategory().getName()))
                                list.add(x);
                }
                else
                    list.add(x);

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
    @PostMapping(value = "/out",consumes =  {"multipart/form-data"})
    public void ot(@ModelAttribute ResturanModel rs)
    {
        System.out.println(rs.getId());
    }


    @Autowired
    private AddresRepository adRep;

    @PostMapping("restaurant/add")
    public Resturan add(@ModelAttribute ResturanModel res)
    {
        Addres ad=new Addres();
        ad.setArea(res.getArea());
        ad.setCity(res.getCity());
        ad.setAddressLine(res.getAddressLine());
        adRep.save(ad);
        Resturan rest=new Resturan();
        rest.setName(res.getName());
        rest.setAddress(ad);
        rest.setAverageRate((double) 0);
        rest.setOpeningTime(res.getOpeningTime());
        rest.setClosingTime(res.getClosingTime());
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

    public byte[] convert(Byte[] byteObjects)
    {
        byte[] bytes=new byte[byteObjects.length];
        int j=0;
        for(Byte b: byteObjects)
            bytes[j++] = b.byteValue();
        return bytes;
    }
}
