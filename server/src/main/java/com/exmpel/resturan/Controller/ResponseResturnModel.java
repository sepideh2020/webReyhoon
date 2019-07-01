package com.exmpel.resturan.Controller;

import com.exmpel.resturan.Entity.Addres;
import com.exmpel.resturan.Entity.Comment;
import com.exmpel.resturan.Entity.Food;
import com.exmpel.resturan.Entity.Resturan_Catagory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

public class ResponseResturnModel {
    private Long id;

    private String name;

    private String logo;

    private String openingTime;
    private String closingTime;
    private Double averageRate;
    private Addres address;
    private List<Resturan_Catagory> categories;
    private List<Food> foods;
    private List<Comment> comments;

    private Boolean open;

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

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

    public String getLogo() {
        return logo;
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
}
