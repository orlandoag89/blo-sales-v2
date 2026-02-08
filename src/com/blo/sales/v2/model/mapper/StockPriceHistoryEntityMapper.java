package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntStockPriceHistory;
import com.blo.sales.v2.model.entities.StockPriceHistoryEntity;
import com.blo.sales.v2.utils.IToOuter;

public class StockPriceHistoryEntityMapper implements IToOuter<StockPriceHistoryEntity, PojoIntStockPriceHistory> {
    
    private static StockPriceHistoryEntityMapper instance;
    
    private StockPriceHistoryEntityMapper() { }
    
    public static StockPriceHistoryEntityMapper getInstance() {
        if (instance == null) {
            instance = new StockPriceHistoryEntityMapper();
        }
        return instance;
    }

    @Override
    public PojoIntStockPriceHistory toOuter(StockPriceHistoryEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoIntStockPriceHistory();
        outer.setCostOfSale(inner.getCostOfSale());
        outer.setId_stock_price_history(inner.getId_stock_price_history());
        outer.setPrice(inner.getPrice());
        outer.setProduct(inner.getProduct());
        outer.setTimestamp(inner.getTimestamp());
        return outer;
    }

}
