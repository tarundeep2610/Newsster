package com.example.newsster;

public class News {
    private String title;
    private String url;
    private String description;
    private String publishedAt;
    private String urlToImage;
    private String author;
    private int id;

    public News(){
        this.id=0;
    }

    public News(News news){
        this.title= news.title;
        this.url= news.url;
        this.description= news.description;
        this.publishedAt= news.publishedAt;
        this.urlToImage= news.urlToImage;
        this.author= news.author;
        this.id= id;
    }

    public News(String title,String author,String url,String description,String publishedAt,String urlToImage){
        this.title= title;
        this.url= url;
        this.description= description;
        this.publishedAt= publishedAt;
        this.urlToImage= urlToImage;
        this.author= author;
        this.id=0;
    }

    public String getTitle(){
        return this.title;
    }

    public String getUrl(){
        return this.url;
    }

    public String getDescription(){
        return this.description;
    }

    public String getPublishedAt(){
        return this.publishedAt;
    }

    public String getUrlToImage(){
        return this.urlToImage;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
