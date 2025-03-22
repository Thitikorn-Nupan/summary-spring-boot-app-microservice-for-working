package com.ttknp.abcmodelsservice.models.mysql;


public class ProductMYSQL {

    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private String sku;
    private Boolean active;

    public ProductMYSQL(Long id, String name, Double price, Integer quantity, String sku, Boolean active) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sku = sku;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
