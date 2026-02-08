package com.blo.sales.v2.model.entities;

import java.math.BigDecimal;

public class PriceHistoryEntity {
    
    private long id_price_history;
    
    private BigDecimal price;
    
    private BigDecimal cost_of_sale;

    public long getId_price_history() {
        return id_price_history;
    }

    public void setId_price_history(long id_price_history) {
        this.id_price_history = id_price_history;
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

    @Override
    public String toString() {
        return "PriceHistoryEntity{" + "id_price_history=" + id_price_history + ", price=" + price + ", cost_of_sale=" + cost_of_sale + '}';
    }
}
