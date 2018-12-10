package com.example.phoebee.superiorproject.model;


public class Markets {
    private long id;
    private String name;
    private double price;
    private String category;
    private String image;
    private String market;
    public Markets(){
    }
    public Markets(String name, double price, String category, String image, String market) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
        this.market = market;
    }
    public Markets(long id,String name, double price, String category, String image, String market) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
        this.market = market;
    }

    public void setId(long id){
        this.id=id;
    }
    public long getId(){
        return this.id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}