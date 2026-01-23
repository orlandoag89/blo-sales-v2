package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntCashboxDetail;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntCashboxesDetails;
import com.blo.sales.v2.model.entities.WrapperCashboxesDetailsEntity;
import com.blo.sales.v2.utils.IToOuter;
import java.util.ArrayList;

public class WrapperCashboxesDetailsEntityMapper implements IToOuter<WrapperCashboxesDetailsEntity, WrapperPojoIntCashboxesDetails> {
    
    private static final CashboxDetailEntityMapper mapper = CashboxDetailEntityMapper.getInstance();
    
    private static WrapperCashboxesDetailsEntityMapper instance;
    
    private WrapperCashboxesDetailsEntityMapper() { }
    
    public static WrapperCashboxesDetailsEntityMapper getInstance() {
        if (instance == null) {
            instance = new WrapperCashboxesDetailsEntityMapper();
        }
        return instance;
    }

    @Override
    public WrapperPojoIntCashboxesDetails toOuter(WrapperCashboxesDetailsEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new WrapperPojoIntCashboxesDetails();
        final var lst = new ArrayList<PojoIntCashboxDetail>();
        if (inner.getCashboxesInfo() != null && !inner.getCashboxesInfo().isEmpty()) {
            inner.getCashboxesInfo().forEach(c -> lst.add(mapper.toOuter(c)));
        }
        outer.setCashboxesInfo(lst);
        return outer;
    }
    
}
