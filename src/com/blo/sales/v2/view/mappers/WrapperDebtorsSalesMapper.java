package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.WrapperPojoIntDebtorsSales;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.pojos.PojoDebtorSale;
import com.blo.sales.v2.view.pojos.WrapperPojoDebtorsSales;
import java.util.ArrayList;

public class WrapperDebtorsSalesMapper implements IToOuter<WrapperPojoIntDebtorsSales, WrapperPojoDebtorsSales> {
    
    private static WrapperDebtorsSalesMapper instance;
    
    private DebtorSaleMapper mapper;
    
    private WrapperDebtorsSalesMapper() {
        mapper = DebtorSaleMapper.getInstance();
    }
    
    public static WrapperDebtorsSalesMapper getInstance() {
        if (instance == null) {
            instance = new WrapperDebtorsSalesMapper();
        }
        return instance;
    }

    @Override
    public WrapperPojoDebtorsSales toOuter(WrapperPojoIntDebtorsSales inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new WrapperPojoDebtorsSales();
        final var items = new ArrayList<PojoDebtorSale>();
        
        if (inner.getDebtorsSales() != null && !inner.getDebtorsSales().isEmpty()) {
            inner.getDebtorsSales().forEach(i -> items.add(mapper.toOuter(i)));
        }
        
        outer.setDebtorsSales(items);
        return outer;
    }
    
}
