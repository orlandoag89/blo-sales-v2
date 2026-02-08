package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntStockPricesHistory;
import com.blo.sales.v2.model.entities.StockPricesHistoryEntity;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;

public class StockPricesHistoryEntityMapper implements IToInner<StockPricesHistoryEntity, PojoIntStockPricesHistory>, IToOuter<StockPricesHistoryEntity, PojoIntStockPricesHistory> {
    
    private static StockPricesHistoryEntityMapper instance;
    
    private StockPricesHistoryEntityMapper() { }
    
    public static StockPricesHistoryEntityMapper getInstance() {
        if (instance == null) {
            instance = new StockPricesHistoryEntityMapper();
        }
        return instance;
    }

    @Override
    public StockPricesHistoryEntity toInner(PojoIntStockPricesHistory outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new StockPricesHistoryEntity();
        inner.setFk_price_history(outer.getFkPriceHistory());
        inner.setFk_product(outer.getFkProduct());
        inner.setId_stock_prices_history(outer.getIdStockPricesHistory());
        inner.setTimesTamp(outer.getTimesTamp());
        return inner;
    }

    @Override
    public PojoIntStockPricesHistory toOuter(StockPricesHistoryEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoIntStockPricesHistory();
        outer.setFkPriceHistory(inner.getFk_price_history());
        outer.setFkProduct(inner.getFk_product());
        outer.setIdStockPricesHistory(inner.getId_stock_prices_history());
        outer.setTimesTamp(inner.getTimesTamp());
        return outer;
    }
    
}
