package com.exmpel.resturan.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "resturans")
public class Resturan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToOne(mappedBy = "resturan",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private com.exmpel.resturan.Entity.Logo Logo;

    private String logo;

    private String openingTime;
    private String closingTime;
    private Double averageRate;

    @OneToOne(mappedBy = "resturan",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private Addres address;

    @OneToMany(mappedBy = "resturan",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private List<Resturan_Catagory> categories;

    @OneToMany(mappedBy = "resturan",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private List<Food> foods;


    @OneToMany(mappedBy = "resturan",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private List<Comment> comments;



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

    public com.exmpel.resturan.Entity.Logo getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public Double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(Double averageRate) {
        this.averageRate = averageRate;
    }

    public Addres getAddress() {
        return address;
    }

    public void setAddress(Addres address) {
        this.address = address;
    }

    public List<Resturan_Catagory> getCategories() {
        return categories;
    }

    public void setCategories(List<Resturan_Catagory> categories) {
        this.categories = categories;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setLogo(com.exmpel.resturan.Entity.Logo logo) {
        Logo = logo;
    }
}
