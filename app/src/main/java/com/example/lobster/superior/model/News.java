package com.example.lobster.superior.model;

public class News {
    private String title;
    private String description;
    private String url;
    private String publishedAt;

    public News(String title, String description, String url, String publishedAt) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
