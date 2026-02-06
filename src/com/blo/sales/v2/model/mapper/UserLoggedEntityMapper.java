package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntLoggedInUser;
import com.blo.sales.v2.controller.pojos.enums.RolesIntEnum;
import com.blo.sales.v2.model.entities.UserEntity;
import com.blo.sales.v2.utils.IToOuter;

public class UserLoggedEntityMapper implements IToOuter<UserEntity, PojoIntLoggedInUser> {
    
    public static UserLoggedEntityMapper instance;
    
    private UserLoggedEntityMapper() { }
    
    public static UserLoggedEntityMapper getInstance() {
        if (instance == null) {
            return new UserLoggedEntityMapper();
        }
        return instance;
    }

    @Override
    public PojoIntLoggedInUser toOuter(UserEntity inner) {
        if (inner == null) {
            return null;
        }
        final var out = new PojoIntLoggedInUser();
        out.setIdUser(inner.getId_user());
        out.setRole(RolesIntEnum.valueOf(inner.getRole().name()));
        out.setUsername(inner.getUsername());
        return out;
    }

}
