package com.blo.sales.v2.controller.pojos;

public class PojoIntStockPricesHistory {
    
    private long idStockPricesHistory;
    
    private long fkProduct;
    
    private long fkPriceHistory;
    
    private String timesTamp;

    public long getIdStockPricesHistory() {
        return idStockPricesHistory;
    }

    public void setIdStockPricesHistory(long idStockPricesHistory) {
        this.idStockPricesHistory = idStockPricesHistory;
    }

    public long getFkProduct() {
        return fkProduct;
    }

    public void setFkProduct(long fkProduct) {
        this.fkProduct = fkProduct;
    }

    public long getFkPriceHistory() {
        return fkPriceHistory;
    }

    public void setFkPriceHistory(long fkPriceHistory) {
        this.fkPriceHistory = fkPriceHistory;
    }

    public String getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(String timesTamp) {
        this.timesTamp = timesTamp;
    }

    @Override
    public String toString() {
        return "PojoIntStockPricesHistory{" + "idStockPricesHistory=" + idStockPricesHistory + ", fkProduct=" + fkProduct + ", fkPriceHistory=" + fkPriceHistory + ", timesTamp=" + timesTamp + '}';
    }
    
}
