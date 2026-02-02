package com.blo.sales.v2.model.entities;

import java.math.BigDecimal;

public class SaleAndProductEntity {
    
    private long id_sale;
    
    private long id_product;
    
    private String product;
    
    private BigDecimal quantity_on_sale;
    
    private BigDecimal price;
    
    private BigDecimal cost_of_sale;
    
    private BigDecimal total_on_sale;
    
    private String timestamp;
    
    private boolean kg;

    public long getId_sale() {
        return id_sale;
    }

    public void setId_sale(long id_sale) {
        this.id_sale = id_sale;
    }

    public long getId_product() {
        return id_product;
    }

    public void setId_product(long id_product) {
        this.id_product = id_product;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getQuantity_on_sale() {
        return quantity_on_sale;
    }

    public void setQuantity_on_sale(BigDecimal quantity_on_sale) {
        this.quantity_on_sale = quantity_on_sale;
    }

    public BigDecimal getTotal_on_sale() {
        return total_on_sale;
    }

    public void setTotal_on_sale(BigDecimal total_on_sale) {
        this.total_on_sale = total_on_sale;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCost_of_sale() {
        return cost_of_sale;
    }

    public void setCost_of_sale(BigDecimal cost_of_sale) {
        this.cost_of_sale = cost_of_sale;
    }

    public boolean isKg() {
        return kg;
    }

    public void setKg(boolean kg) {
        this.kg = kg;
    }

    @Override
    public String toString() {
        return "SaleAndProductEntity{" + "id_sale=" + id_sale + ", id_product=" + id_product + ", product=" + product + ", quantity_on_sale=" + quantity_on_sale + ", price=" + price + ", cost_of_sale=" + cost_of_sale + ", total_on_sale=" + total_on_sale + ", timestamp=" + timestamp + ", kg=" + kg + '}';
    }
    
    
}
