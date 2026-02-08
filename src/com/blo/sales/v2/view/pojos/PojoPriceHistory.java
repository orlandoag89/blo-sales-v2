package com.blo.sales.v2.view.pojos;

import java.math.BigDecimal;

public class PojoPriceHistory {
    
    private long idPriceHistory;
    
    private BigDecimal price;
    
    private BigDecimal costOfSale;

    public long getIdPriceHistory() {
        return idPriceHistory;
    }

    public void setIdPriceHistory(long idPriceHistory) {
        this.idPriceHistory = idPriceHistory;
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

    @Override
    public String toString() {
        return "PojoIntPriceHistory{" + "idPriceHistory=" + idPriceHistory + ", price=" + price + ", costOfSale=" + costOfSale + '}';
    }
    
}
