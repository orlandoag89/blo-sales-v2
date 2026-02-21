package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntMovement;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntMovementsDetail;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IHistoryController {
    
    PojoIntMovement registerMovement(PojoIntMovement movement) throws BloSalesV2Exception;
    
    WrapperPojoIntMovementsDetail getHistoryFromProduct(long productId) throws BloSalesV2Exception;
    
}
