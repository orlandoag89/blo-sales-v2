package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.IUserController;
import com.blo.sales.v2.controller.pojos.PojoIntLoggedInUser;
import com.blo.sales.v2.controller.pojos.PojoIntUser;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public class UserControllerImpl implements IUserController {

    @Override
    public PojoIntLoggedInUser doLogin(PojoIntUser userData) throws BloSalesV2Exception {
        System.err.println(userData.getPassword() + "  " + userData.getUserName());
        return null;
    }
    
}
