package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntMovement;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IHistoryModel {
    
    PojoIntMovement registerMovement(PojoIntMovement movement) throws BloSalesV2Exception;
}
