package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.beans.BeanIntLoggedInUser;
import com.blo.sales.v2.controller.beans.BeanIntUser;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IUserController {
    
    BeanIntLoggedInUser doLogin(BeanIntUser userData) throws BloSalesV2Exception;
}
