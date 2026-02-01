package com.blo.sales.v2.view.commons.plugins.sales.report;

import java.math.BigDecimal;

public class RowSaleReportData {
    
    private long idProduct;
    
    private String product;
    
    private BigDecimal quantityOnSale;
    
    private BigDecimal price;
    
    private BigDecimal costOfSale;
    
    private BigDecimal totalOnSale;
    
    private String timestamp;

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

    @Override
    public String toString() {
        return String.format("Producto: %s | Cantidad: %s | Precio: $%s | Costo: $%s", 
                         product, quantityOnSale, price, costOfSale);
    }
}
