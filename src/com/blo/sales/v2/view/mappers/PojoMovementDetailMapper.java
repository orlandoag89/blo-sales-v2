package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntMovementDetail;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.pojos.PojoMovementDetail;
import com.blo.sales.v2.view.pojos.enums.ReasonsEnum;
import com.blo.sales.v2.view.pojos.enums.TypesEnum;

public class PojoMovementDetailMapper implements IToOuter<PojoIntMovementDetail, PojoMovementDetail>{
    
    private static PojoMovementDetailMapper instance;
    
    private PojoMovementDetailMapper() { }
    
    public static PojoMovementDetailMapper getInstance() {
        if (instance == null) {
            instance = new PojoMovementDetailMapper();
        }
        return instance;
    }

    @Override
    public PojoMovementDetail toOuter(PojoIntMovementDetail inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoMovementDetail();
        outer.setIdMovement(inner.getIdMovement());
        outer.setIdProduct(inner.getIdProduct());
        outer.setProduct(inner.getProduct());
        outer.setReason(ReasonsEnum.valueOf(inner.getReason().name()));
        outer.setTimestamp(inner.getTimestamp());
        outer.setType(TypesEnum.valueOf(inner.getType().name()));
        outer.setUsername(inner.getUsername());
        outer.setQuantity(inner.getQuantity());
        return outer;
    }
    
}
