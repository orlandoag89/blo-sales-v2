package com.blo.sales.v2.model.entities;

import java.math.BigDecimal;

public class ProductEntity {
    
    private int id_product;
    
    private String product;
    
    private BigDecimal quantity;
    
    private BigDecimal cost_of_sale;
    
    private BigDecimal price;
    
    private String timestamp;
    
    private boolean kg;
    
    private String bar_code;
    
    private int fk_category;

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCost_of_sale() {
        return cost_of_sale;
    }

    public void setCost_of_sale(BigDecimal cost_of_sale) {
        this.cost_of_sale = cost_of_sale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isKg() {
        return kg;
    }

    public void setKg(boolean kg) {
        this.kg = kg;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public int getFk_category() {
        return fk_category;
    }

    public void setFk_category(int fk_category) {
        this.fk_category = fk_category;
    }
    
}
