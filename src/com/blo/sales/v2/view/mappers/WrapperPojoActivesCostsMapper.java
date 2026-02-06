package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntActiveCost;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntActivesCosts;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.view.pojos.WrapperPojoActivesCosts;
import java.util.ArrayList;

public class WrapperPojoActivesCostsMapper implements IToInner<WrapperPojoIntActivesCosts, WrapperPojoActivesCosts> {
    
    private static final PojoActiveCostMapper mapper = PojoActiveCostMapper.getInstance();
    
    private static WrapperPojoActivesCostsMapper instance;
    
    private WrapperPojoActivesCostsMapper() { }
    
    public static WrapperPojoActivesCostsMapper getInstance() {
        if (instance == null) {
            instance = new WrapperPojoActivesCostsMapper();
        }
        return instance;
    }

    @Override
    public WrapperPojoIntActivesCosts toInner(WrapperPojoActivesCosts outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new WrapperPojoIntActivesCosts();
        final var activesCosts = new ArrayList<PojoIntActiveCost>();
        if (outer.getActivesCosts() != null && !outer.getActivesCosts().isEmpty()) {
            outer.getActivesCosts().forEach(c -> activesCosts.add(mapper.toInner(c)));
        }
        inner.setActivesCosts(activesCosts);
        return inner;
    }
    
}
