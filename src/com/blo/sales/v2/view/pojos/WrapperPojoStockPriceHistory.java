package com.blo.sales.v2.view.pojos;

import java.util.List;

public class WrapperPojoStockPriceHistory {
    
    private List<PojoStockPriceHistory> history;

    public List<PojoStockPriceHistory> getHistory() {
        return history;
    }

    public void setHistory(List<PojoStockPriceHistory> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "WrapperPojoIntStockPriceHistory{" + "history=" + history + '}';
    }
    
}
