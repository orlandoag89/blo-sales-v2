package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.WrapperPojoIntStockPriceHistory;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.pojos.PojoStockPriceHistory;
import com.blo.sales.v2.view.pojos.WrapperPojoStockPriceHistory;
import java.util.ArrayList;

public class WrapperPojoStockPriceHistoryMapper implements IToOuter<WrapperPojoIntStockPriceHistory, WrapperPojoStockPriceHistory> {
    
    private static final PojoStockPriceHistoryMapper mapper = PojoStockPriceHistoryMapper.getInstance();
    
    private static WrapperPojoStockPriceHistoryMapper instance;
    
    private WrapperPojoStockPriceHistoryMapper() { }
    
    public static WrapperPojoStockPriceHistoryMapper getInstance() {
        if (instance == null) {
            instance = new WrapperPojoStockPriceHistoryMapper();
        }
        return instance;
    }

    @Override
    public WrapperPojoStockPriceHistory toOuter(WrapperPojoIntStockPriceHistory inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new WrapperPojoStockPriceHistory();
        final var lst = new ArrayList<PojoStockPriceHistory>();
        
        if (inner.getHistory() != null && !inner.getHistory().isEmpty()) {
            inner.getHistory().forEach(i -> lst.add(mapper.toOuter(i)));
        }
        outer.setHistory(lst);
        return outer;
    }
    
}
