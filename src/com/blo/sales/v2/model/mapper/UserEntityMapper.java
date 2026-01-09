package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntLoggedInUser;
import com.blo.sales.v2.controller.pojos.enums.RolesIntEnum;
import com.blo.sales.v2.model.entities.UserEntity;
import com.blo.sales.v2.utils.IToOuter;

public class UserEntityMapper implements IToOuter<UserEntity, PojoIntLoggedInUser> {

    @Override
    public PojoIntLoggedInUser toOuter(UserEntity inner) {
        if (inner == null) {
            return null;
        }
        final var out = new PojoIntLoggedInUser();
        out.setRole(RolesIntEnum.valueOf(inner.getRole().name()));
        out.setUsername(inner.getUsername());
        return out;
    }

}
