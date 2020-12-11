package com.hello.cakeapp;

public class CakeTable {
    private int id;
    private String cakename;
    private String title;
    private String weight;
    private String type;
    private String cost;
    private String detail;
    private byte[] iv;
    public CakeTable(String cakename, String title, String weight, String type, String cost, String detail, byte[] iv, int id) {
        this.cakename = cakename;
        this.title = title;
        this.weight=weight;
        this.type=type;
        this.cost=cost;
        this.detail=detail;
        this.iv = iv;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCakename() {
        return cakename;
    }

    public void setCakename(String cakename) {
        this.cakename = cakename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public byte[] getIv() {
        return iv;
    }

    public void setIv(byte[] iv) {
        this.iv = iv;
    }


}
