package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntCashbox;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntCashboxes;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntCashboxesDetails;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface ICashboxModel {
    
    PojoIntCashbox addCashbox(PojoIntCashbox cashbox) throws BloSalesV2Exception;
    
    PojoIntCashbox updateCashbox(PojoIntCashbox cashbox, long idCashbox) throws BloSalesV2Exception;
    
    PojoIntCashbox getOpenCashbox() throws BloSalesV2Exception;
    
    WrapperPojoIntCashboxes getAllCashboxes() throws BloSalesV2Exception;
    
    WrapperPojoIntCashboxesDetails getCashboxesDetail() throws BloSalesV2Exception;
}
