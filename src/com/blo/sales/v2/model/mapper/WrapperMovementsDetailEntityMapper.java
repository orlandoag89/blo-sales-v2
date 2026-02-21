package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntMovementDetail;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntMovementsDetail;
import com.blo.sales.v2.model.entities.WrapperMovementsDetailEntity;
import com.blo.sales.v2.utils.IToOuter;
import java.util.ArrayList;

public class WrapperMovementsDetailEntityMapper implements IToOuter<WrapperMovementsDetailEntity, WrapperPojoIntMovementsDetail> {
    
    private static final MovementDetailEntityMapper mapper = MovementDetailEntityMapper.getInstance();
    
    private static WrapperMovementsDetailEntityMapper instance;
    
    private WrapperMovementsDetailEntityMapper() { }
    
    public static WrapperMovementsDetailEntityMapper getInstance() {
        if (instance == null) {
            instance = new WrapperMovementsDetailEntityMapper();
        }
        return instance;
    }

    @Override
    public WrapperPojoIntMovementsDetail toOuter(WrapperMovementsDetailEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new WrapperPojoIntMovementsDetail();
        final var lst = new ArrayList<PojoIntMovementDetail>();
        if (inner.getHistory() != null && !inner.getHistory().isEmpty()) {
            inner.getHistory().forEach(h -> lst.add(mapper.toOuter(h)));
        }
        outer.setHistory(lst);
        return outer;
    }
    
}
