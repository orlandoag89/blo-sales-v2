package com.blo.sales.v2.model.entities;

public class StockPricesHistoryEntity {
    
    private long id_stock_prices_history;
    
    private long fk_product;
    
    private long fk_price_history;
    
    private String timesTamp;    

    public long getId_stock_prices_history() {
        return id_stock_prices_history;
    }

    public void setId_stock_prices_history(long id_stock_prices_history) {
        this.id_stock_prices_history = id_stock_prices_history;
    }

    public long getFk_product() {
        return fk_product;
    }

    public void setFk_product(long fk_product) {
        this.fk_product = fk_product;
    }

    public long getFk_price_history() {
        return fk_price_history;
    }

    public void setFk_price_history(long fk_price_history) {
        this.fk_price_history = fk_price_history;
    }

    public String getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(String timesTamp) {
        this.timesTamp = timesTamp;
    }

    @Override
    public String toString() {
        return "StockPricesHistoryEntity{" + "id_stock_prices_history=" + id_stock_prices_history + ", fk_product=" + fk_product + ", fk_price_history=" + fk_price_history + ", timesTamp=" + timesTamp + '}';
    }
}
