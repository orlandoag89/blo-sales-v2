package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntCashboxesActivesCosts;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface ICashboxesActivesCostsController {
    
    PojoIntCashboxesActivesCosts addRelationship(PojoIntCashboxesActivesCosts data) throws BloSalesV2Exception;
    
}
