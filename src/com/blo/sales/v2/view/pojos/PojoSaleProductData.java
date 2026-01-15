package com.blo.sales.v2.view.pojos;

import java.math.BigDecimal;

public class PojoSaleProductData {
    
    private long idProduct;
    
    private BigDecimal quantityOnSale;
    
    private BigDecimal price;
    
    private String timestamp;

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
}
