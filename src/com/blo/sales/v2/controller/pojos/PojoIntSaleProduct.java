package com.blo.sales.v2.controller.pojos;

import java.math.BigDecimal;

public class PojoIntSaleProduct {
    
    private long idSaleProduct;
    
    private long fkSale;
    
    private long fkProduct;
    
    private BigDecimal quantityOnSale;
    
    private BigDecimal totalOnSale;
    
    private String timestap;

    public long getIdSaleProduct() {
        return idSaleProduct;
    }

    public void setIdSaleProduct(long idSaleProduct) {
        this.idSaleProduct = idSaleProduct;
    }

    public long getFkSale() {
        return fkSale;
    }

    public void setFkSale(long fkSale) {
        this.fkSale = fkSale;
    }

    public long getFkProduct() {
        return fkProduct;
    }

    public void setFkProduct(long fkProduct) {
        this.fkProduct = fkProduct;
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

    public String getTimestap() {
        return timestap;
    }

    public void setTimestap(String timestap) {
        this.timestap = timestap;
    }
    
}
