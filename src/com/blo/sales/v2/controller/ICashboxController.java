package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntCashbox;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntCashboxes;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface ICashboxController {
    
    PojoIntCashbox addCashbox(PojoIntCashbox cashbox) throws BloSalesV2Exception;
        
    PojoIntCashbox updateCAshbox(PojoIntCashbox cashbox, long idCashbox) throws BloSalesV2Exception;
    
    PojoIntCashbox getOpenCashbox() throws BloSalesV2Exception;
    
    WrapperPojoIntCashboxes getAllCashboxes() throws BloSalesV2Exception;
}
