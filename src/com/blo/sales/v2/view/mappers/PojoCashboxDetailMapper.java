package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntCashboxDetail;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.pojos.PojoCashboxDetail;
import com.blo.sales.v2.view.pojos.enums.ActivesCostsEnum;
import com.blo.sales.v2.view.pojos.enums.CashboxStatusEnum;

public class PojoCashboxDetailMapper implements IToOuter<PojoIntCashboxDetail, PojoCashboxDetail>{
    
    private static PojoCashboxDetailMapper instance;
    
    private PojoCashboxDetailMapper() {}
    
    public static PojoCashboxDetailMapper getInstance() {
        if (instance == null) {
            instance = new PojoCashboxDetailMapper();
        }
        return instance;
    }

    @Override
    public PojoCashboxDetail toOuter(PojoIntCashboxDetail inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoCashboxDetail();
        outer.setAmount(inner.getAmount());
        outer.setConcept(inner.getConcept());
        outer.setIdCashbox(inner.getIdCashbox());
        outer.setStatus(CashboxStatusEnum.valueOf(inner.getStatus().name()));
        outer.setTimestamp(inner.getTimestamp());
        outer.setType(ActivesCostsEnum.valueOf(inner.getType().name()));
        return outer;
    }
}
