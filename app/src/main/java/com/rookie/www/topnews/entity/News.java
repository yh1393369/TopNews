package com.rookie.www.topnews.entity;

/**
 * Created by Hi on 2017/2/9.
 */

public class News {

    private String title;
    private String date;
    private String autor;
    private String url;
    private String imageUrl;

    public News(String title, String date, String autor, String url, String imageUrl) {
        this.title = title;
        this.date = date;
        this.autor = autor;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
