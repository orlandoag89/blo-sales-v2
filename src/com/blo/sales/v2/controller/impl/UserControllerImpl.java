package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.IUserController;
import com.blo.sales.v2.controller.pojos.PojoIntLoggedInUser;
import com.blo.sales.v2.controller.pojos.PojoIntUser;
import com.blo.sales.v2.model.IUserModel;
import com.blo.sales.v2.model.impl.UserModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public class UserControllerImpl implements IUserController {
    
    private IUserModel userModel;
    
    public UserControllerImpl() {
        userModel = new UserModelImpl();
    }

    @Override
    public PojoIntLoggedInUser doLogin(PojoIntUser userData) throws BloSalesV2Exception {
        return userModel.doLogin(userData);
    }
    
}
