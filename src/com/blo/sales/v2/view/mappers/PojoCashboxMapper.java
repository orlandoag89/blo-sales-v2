package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntCashbox;
import com.blo.sales.v2.controller.pojos.enums.CashboxStatusIntEnum;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.pojos.PojoCashbox;
import com.blo.sales.v2.view.pojos.enums.CashboxStatusEnum;

public class PojoCashboxMapper implements IToOuter<PojoIntCashbox, PojoCashbox>, IToInner<PojoIntCashbox, PojoCashbox> {
    
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

    @Override
    public PojoIntCashbox toInner(PojoCashbox outer) {
         if (outer == null) {
            return null;
        }
        final var inner = new PojoIntCashbox();
        inner.setAmount(outer.getAmount());
        inner.setFkUser(outer.getFkUser());
        inner.setIdCashbox(outer.getIdCashbox());
        inner.setTimestamp(outer.getTimestamp());
        inner.setStatus(CashboxStatusIntEnum.valueOf(outer.getStatus().name()));
        inner.setUserFrom(outer.getUserFrom());
        return inner;
    }
    
}
