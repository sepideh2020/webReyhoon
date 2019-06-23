package com.exmpel.resturan.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @OneToMany(mappedBy = "category",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private List<Resturan_Catagory> resturan_catagories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Resturan_Catagory> getResturan_catagories() {
        return resturan_catagories;
    }

    public void setResturan_catagories(List<Resturan_Catagory> resturan_catagories) {
        this.resturan_catagories = resturan_catagories;
    }
}
