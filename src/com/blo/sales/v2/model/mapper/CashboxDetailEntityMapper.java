package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntCashboxDetail;
import com.blo.sales.v2.controller.pojos.enums.ActiveCostIntEnum;
import com.blo.sales.v2.controller.pojos.enums.CashboxStatusIntEnum;
import com.blo.sales.v2.model.entities.CashboxDetailEntity;
import com.blo.sales.v2.utils.IToOuter;

public class CashboxDetailEntityMapper implements IToOuter<CashboxDetailEntity, PojoIntCashboxDetail>{
    
    private static CashboxDetailEntityMapper instance;
    
    private CashboxDetailEntityMapper() {}
    
    public static CashboxDetailEntityMapper getInstance() {
        if (instance == null) {
            instance = new CashboxDetailEntityMapper();
        }
        return instance;
    }

    @Override
    public PojoIntCashboxDetail toOuter(CashboxDetailEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoIntCashboxDetail();
        outer.setAmount(inner.getAmount());
        outer.setConcept(inner.getConcept());
        outer.setIdCashbox(inner.getId_cashbox());
        outer.setStatus(CashboxStatusIntEnum.valueOf(inner.getStatus().name()));
        outer.setTimestamp(inner.getTimestamp());
        outer.setType(ActiveCostIntEnum.valueOf(inner.getType().name()));
        return outer;
    }
    
}
