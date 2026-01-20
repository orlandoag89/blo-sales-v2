package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntCashbox;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntCashboxes;
import com.blo.sales.v2.model.entities.WrapperCashboxesEntity;
import com.blo.sales.v2.utils.IToOuter;
import java.util.ArrayList;

public class WrapperCashboxesEntityMapper implements IToOuter<WrapperCashboxesEntity, WrapperPojoIntCashboxes> {
    
    private static WrapperCashboxesEntityMapper instance;
    
    private static final CashboxEntityMapper mapper = CashboxEntityMapper.getInstance();
    
    private WrapperCashboxesEntityMapper() { }
    
    public static WrapperCashboxesEntityMapper getInstance() {
        if (instance == null) {
            instance = new WrapperCashboxesEntityMapper();
        }
        return instance;
    }

    @Override
    public WrapperPojoIntCashboxes toOuter(WrapperCashboxesEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new WrapperPojoIntCashboxes();
        final var lst = new ArrayList<PojoIntCashbox>();
        if (inner.getCashboxes() != null && !inner.getCashboxes().isEmpty()) {
            inner.getCashboxes().forEach(c -> lst.add(mapper.toOuter(c)));
        }
        outer.setCashboxes(lst);
        return outer;
    }
    
}
