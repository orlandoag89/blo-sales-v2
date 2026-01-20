package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntCashbox;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.pojos.PojoCashbox;
import com.blo.sales.v2.view.pojos.enums.CashboxStatusEnum;

public class PojoCashboxMapper implements IToOuter<PojoIntCashbox, PojoCashbox> {
    
    private static PojoCashboxMapper instance;
    
    private PojoCashboxMapper() { }
    
    public static PojoCashboxMapper getInstance() {
        if (instance == null) {
            instance = new PojoCashboxMapper();
        }
        return instance;
    }

    @Override
    public PojoCashbox toOuter(PojoIntCashbox inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoCashbox();
        outer.setAmount(inner.getAmount());
        outer.setFkUser(inner.getFkUser());
        outer.setIdCashbox(inner.getIdCashbox());
        outer.setTimestamp(inner.getTimestamp());
        outer.setStatus(CashboxStatusEnum.valueOf(inner.getStatus().name()));
        outer.setUserFrom(inner.getUserFrom());
        return outer;
    }
    
}
