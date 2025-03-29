package com.ttknp.abcmodelsservice.models.mysql;

import java.sql.Timestamp;


public class ToyMYSQL {

    private Long tid;
    private String name;
    private String status;
    private Double price;
    private Timestamp releaseDate; // should map 2021-02-27 19:08:29

    public ToyMYSQL(Long tid, String name, String status, Double price, Timestamp releaseDate) {
        this.tid = tid;
        this.name = name;
        this.status = status;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }
}
