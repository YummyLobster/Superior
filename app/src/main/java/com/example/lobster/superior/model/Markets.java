package com.example.lobster.superior.model;

public class Markets {
    private String name;
    private String price;
    private String category;
    private String image;
    private String market;
    public Markets(String name, String price, String category, String image, String market) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
