package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.WrapperPojoIntMovementsDetail;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.pojos.PojoMovementDetail;
import com.blo.sales.v2.view.pojos.WrapperPojoMovementsDetail;
import java.util.ArrayList;

public class WrapperPojoMovementsDetailMapper implements IToOuter<WrapperPojoIntMovementsDetail, WrapperPojoMovementsDetail> {
    
    private static final PojoMovementDetailMapper mapper = PojoMovementDetailMapper.getInstance();
    
    private static WrapperPojoMovementsDetailMapper instance;
    
    private WrapperPojoMovementsDetailMapper() { }
    
    public static WrapperPojoMovementsDetailMapper getInstance() {
        if (instance == null) {
            instance = new WrapperPojoMovementsDetailMapper();
        }
        return instance;
    }

    @Override
    public WrapperPojoMovementsDetail toOuter(WrapperPojoIntMovementsDetail inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new WrapperPojoMovementsDetail();
        final var lst = new ArrayList<PojoMovementDetail>();
        if (inner.getHistory() != null && !inner.getHistory().isEmpty()) {
            inner.getHistory().forEach(h -> lst.add(mapper.toOuter(h)));
        }
        outer.setHistory(lst);
        return outer;
    }
    
}
