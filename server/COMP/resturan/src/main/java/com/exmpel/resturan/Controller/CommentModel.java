package com.exmpel.resturan.Controller;

public class CommentModel {
    String author;
    Integer rate;
    String text;
    String deleverytime;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDeleverytime() {
        return deleverytime;
    }

    public void setDeleverytime(String deleverytime) {
        this.deleverytime = deleverytime;
    }
}
