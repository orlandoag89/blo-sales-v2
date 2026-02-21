package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntMovementDetail;
import com.blo.sales.v2.controller.pojos.enums.ReasonsIntEnum;
import com.blo.sales.v2.controller.pojos.enums.TypesIntEnum;
import com.blo.sales.v2.model.entities.MovementDetailEntity;
import com.blo.sales.v2.utils.IToOuter;

public class MovementDetailEntityMapper implements IToOuter<MovementDetailEntity, PojoIntMovementDetail>{
    
    private static MovementDetailEntityMapper instance;
    
    private MovementDetailEntityMapper() { }
    
    public static MovementDetailEntityMapper getInstance() {
        if (instance == null) {
            instance = new MovementDetailEntityMapper();
        }
        return instance;
    }

    @Override
    public PojoIntMovementDetail toOuter(MovementDetailEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoIntMovementDetail();
        outer.setIdMovement(inner.getId_movement());
        outer.setIdProduct(inner.getId_product());
        outer.setProduct(inner.getProduct());
        outer.setReason(ReasonsIntEnum.valueOf(inner.getReason().name()));
        outer.setTimestamp(inner.getTimestamp());
        outer.setType(TypesIntEnum.valueOf(inner.getType().name()));
        outer.setUsername(inner.getUsername());
        outer.setQuantity(inner.getQuantity());
        return outer;
    }
    
}
