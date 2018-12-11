package com.example.phoebee.superiorproject.model;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class Note extends DataSupport implements Serializable {
    private int id;
    private String name;
    private String time;
    private String money;

    public Note(String name, String time, String money) {
        this.name = name;
        this.time = time;
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
