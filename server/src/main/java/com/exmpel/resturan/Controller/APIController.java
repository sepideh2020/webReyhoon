package com.exmpel.resturan.Controller;


import com.exmpel.resturan.Entity.*;
import com.exmpel.resturan.Service.AddresRepository;
import com.exmpel.resturan.Service.CommentRepository;
import com.exmpel.resturan.Service.ResturanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public Comment addcomment(@PathVariable Long id,@RequestParam String author,Integer rate,String text,String deleverytime)
    {
        Comment com=new Comment();
        com.setCreated_at(new Date());
        com.setAuthor(author);
        com.setDeliveryTime(deleverytime);
        com.setQuality(rate);
//        com.setPackaging(2);
        com.setText(text);
        Resturan res=resturanRep.findById(id).get();
        com.setResturan(res);
        Double avg=(res.getAverageRate()*res.getComments().size()+rate)/res.getComments().size()+1;
        res.setAverageRate(avg);
        resturanRep.save(res);
        return comRep.save(com);
    }
    @Autowired
    private AddresRepository adRep;

    @PostMapping("restaurant/add")
    public Resturan add(@RequestBody RestModel res)
    {
        Addres ad=new Addres();
        ad.setArea(res.getArea());
        ad.setCity(res.getCity());
        ad.setAddressLine(res.getAddressLine());
        adRep.save(ad);
        Resturan rest=new Resturan();
        rest.setName(res.getName());
        rest.setAddress(ad);
        rest.setOpeningTime(res.getOpeningTime());
        rest.setClosingTime(res.getClosingTime());
        return resturanRep.save(rest);
    }

}
