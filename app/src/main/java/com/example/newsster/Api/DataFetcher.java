package com.example.newsster.Api;

import com.example.newsster.News;

import java.util.ArrayList;

public class DataFetcher {
    private String status;
    private int totalResults;
    private ArrayList<News> articles;

    public DataFetcher(String status, int totalResults, ArrayList<News> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<News> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<News> articles) {
        this.articles = articles;
    }
}
