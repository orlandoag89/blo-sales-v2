package com.blo.sales.v2.view.windows.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntLoggedInUser;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.windows.pojos.PojoLoggedInUser;
import com.blo.sales.v2.view.windows.pojos.enums.RolesEnum;

public class LoggedInUserMapper implements IToOuter<PojoIntLoggedInUser, PojoLoggedInUser> {

    @Override
    public PojoLoggedInUser toOuter(PojoIntLoggedInUser inner) {
        if (inner == null) {
            return null;
        }
        final var out = new PojoLoggedInUser();
        out.setRole(RolesEnum.valueOf(inner.getRole().name()));
        out.setUsername(inner.getUsername());
        return out;
    }
    
}
