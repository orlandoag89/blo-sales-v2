package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntDebtorSale;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntDebtorsSales;
import com.blo.sales.v2.model.entities.WrapperDebtorsSalesEntity;
import com.blo.sales.v2.utils.IToOuter;
import java.util.ArrayList;

public class WrapperDebtorsSalesEntityMapper implements IToOuter<WrapperDebtorsSalesEntity, WrapperPojoIntDebtorsSales> {
    
    private static WrapperDebtorsSalesEntityMapper instance;
    
    private DebtorSaleEntityMapper mapper;
    
    private WrapperDebtorsSalesEntityMapper() {
        mapper = DebtorSaleEntityMapper.getInstance();
    }
    
    public static WrapperDebtorsSalesEntityMapper getInstance() {
        if (instance == null) {
            instance = new WrapperDebtorsSalesEntityMapper();
        }
        return instance;
    }

    @Override
    public WrapperPojoIntDebtorsSales toOuter(WrapperDebtorsSalesEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new WrapperPojoIntDebtorsSales();
        final var items = new ArrayList<PojoIntDebtorSale>();
        
        if (inner.getDebtorsSales() != null && !inner.getDebtorsSales().isEmpty()) {
            inner.getDebtorsSales().forEach(i -> items.add(mapper.toOuter(i)));
        }
        
        outer.setDebtorsSales(items);
        return outer;
    }
    
}
