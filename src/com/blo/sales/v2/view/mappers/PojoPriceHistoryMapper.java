package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntPriceHistory;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.view.pojos.PojoPriceHistory;

public class PojoPriceHistoryMapper implements IToInner<PojoIntPriceHistory, PojoPriceHistory> {
    
    private static PojoPriceHistoryMapper instance;
    
    private PojoPriceHistoryMapper() { }
    
    public static PojoPriceHistoryMapper getInstance() {
        if (instance == null) {
            instance = new PojoPriceHistoryMapper();
        }
        return instance;
    }

    @Override
    public PojoIntPriceHistory toInner(PojoPriceHistory outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new PojoIntPriceHistory();
        inner.setCostOfSale(outer.getCostOfSale());
        inner.setIdPriceHistory(outer.getIdPriceHistory());
        inner.setPrice(outer.getPrice());
        return inner;
    }
    
}
