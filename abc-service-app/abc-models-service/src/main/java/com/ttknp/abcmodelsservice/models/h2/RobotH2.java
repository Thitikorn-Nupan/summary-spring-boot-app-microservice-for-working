package com.ttknp.abcmodelsservice.models.h2;

public class RobotH2 {
    private Long rid;
    private String codename;
    private String releaseDate;
    private Double price;
    private Boolean status;

    public RobotH2(Long rid, String codename, String releaseDate, Double price, Boolean status) {
        this.rid = rid;
        this.codename = codename;
        this.releaseDate = releaseDate;
        this.price = price;
        this.status = status;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
