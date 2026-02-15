package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntSaleProduct;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntSaleStock;
import com.blo.sales.v2.model.entities.WrapperSaleStockEntity;
import com.blo.sales.v2.utils.IToOuter;
import java.util.ArrayList;

public class WrapperSaleStockEntityMapper implements IToOuter<WrapperSaleStockEntity, WrapperPojoIntSaleStock> {
    
    private static final SaleProductEntityMapper mapper = SaleProductEntityMapper.getInstance();
    
    private static WrapperSaleStockEntityMapper instance;
    
    private WrapperSaleStockEntityMapper() { }
    
    public static WrapperSaleStockEntityMapper getInstance() {
        if (instance == null) {
            instance = new WrapperSaleStockEntityMapper();
        }
        return instance;
    }

    @Override
    public WrapperPojoIntSaleStock toOuter(WrapperSaleStockEntity inner) {
        if (inner == null) {
            return null;
        }
        final var out = new WrapperPojoIntSaleStock();
        final var salesStock = new ArrayList<PojoIntSaleProduct>();
        
        if (inner.getSalesStock() != null && !inner.getSalesStock().isEmpty()) {
            inner.getSalesStock().forEach(s -> salesStock.add(mapper.toOuter(s)));
        }
        
        out.setSalesStocks(salesStock);
        return out;
        
    }
    
}
