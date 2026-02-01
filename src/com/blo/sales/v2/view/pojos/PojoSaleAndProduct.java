package com.blo.sales.v2.view.pojos;

import java.math.BigDecimal;

public class PojoSaleAndProduct {
    
    private long idSale;
    
    private long idProduct;
    
    private String product;
    
    private BigDecimal quantityOnSale;
    
    private BigDecimal price;
    
    private BigDecimal costOfSale;
    
    private BigDecimal totalOnSale;
    
    private String timestamp;
    
    private boolean kg;

    public long getIdSale() {
        return idSale;
    }

    public void setIdSale(long idSale) {
        this.idSale = idSale;
    }

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

    public BigDecimal getQuantityOnSale() {
        return quantityOnSale;
    }

    public void setQuantityOnSale(BigDecimal quantityOnSale) {
        this.quantityOnSale = quantityOnSale;
    }

    public BigDecimal getTotalOnSale() {
        return totalOnSale;
    }

    public void setTotalOnSale(BigDecimal totalOnSale) {
        this.totalOnSale = totalOnSale;
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

    public BigDecimal getCostOfSale() {
        return costOfSale;
    }

    public void setCostOfSale(BigDecimal costOfSale) {
        this.costOfSale = costOfSale;
    }

    public boolean isKg() {
        return kg;
    }

    public void setKg(boolean kg) {
        this.kg = kg;
    }

    @Override
    public String toString() {
        return "PojoSaleAndProduct{" + "idSale=" + idSale + ", idProduct=" + idProduct + ", product=" + product + ", quantityOnSale=" + quantityOnSale + ", price=" + price + ", costOfSale=" + costOfSale + ", totalOnSale=" + totalOnSale + ", timestamp=" + timestamp + ", kg=" + kg + '}';
    }

}
