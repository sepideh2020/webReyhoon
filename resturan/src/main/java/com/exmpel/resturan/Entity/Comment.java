package com.exmpel.resturan.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author",nullable = false)
    private String Author;

    @Column(name = "quality")
    private Integer Quality;

    @Column(nullable = false)
    private Integer packaging;

    @Column(nullable = false)
    private String deliveryTime;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Date created_at;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resturan_id")
    private Resturan resturan;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Integer getQuality() {
        return Quality;
    }

    public void setQuality(Integer quality) {
        Quality = quality;
    }

    public Integer getPackaging() {
        return packaging;
    }

    public void setPackaging(Integer packaging) {
        this.packaging = packaging;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Resturan getResturan() {
        return resturan;
    }

    public void setResturan(Resturan resturan) {
        this.resturan = resturan;
    }
}
