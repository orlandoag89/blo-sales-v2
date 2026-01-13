package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntSale;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface ISalesController {
    
    PojoIntSale registerSale(PojoIntSale sale, int idUser) throws BloSalesV2Exception;
    
}
