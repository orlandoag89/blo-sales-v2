package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntDebtorSale;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IDebtorsSalesController {
    
    PojoIntDebtorSale addRelationship(PojoIntDebtorSale debtor) throws BloSalesV2Exception;
    
}
