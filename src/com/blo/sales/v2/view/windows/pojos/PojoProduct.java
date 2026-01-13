package com.blo.sales.v2.view.windows.pojos;

import java.math.BigDecimal;

public class PojoProduct {
    
    private long idProduct;
    
    private String product;
    
    private BigDecimal quantity;
    
    private BigDecimal costOfSale;
    
    private BigDecimal price;
    
    private boolean kg;
    
    private String barCode;
    
    private long fkCategory;

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
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

    public BigDecimal getCostOfSale() {
        return costOfSale;
    }

    public void setCostOfSale(BigDecimal costOfSale) {
        this.costOfSale = costOfSale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isKg() {
        return kg;
    }

    public void setKg(boolean kg) {
        this.kg = kg;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public long getFkCategory() {
        return fkCategory;
    }

    public void setFkCategory(long fkCategory) {
        this.fkCategory = fkCategory;
    }

    @Override
    public String toString() {
        return "[" + this.idProduct + "] " + this.product + " $" + this.price;
    }
    
}
