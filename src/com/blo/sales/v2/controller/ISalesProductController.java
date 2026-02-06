package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntSaleProduct;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntSalesAndStock;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface ISalesProductController {
    
    PojoIntSaleProduct addSalesProduct(PojoIntSaleProduct salesProduct) throws BloSalesV2Exception;
    
}
