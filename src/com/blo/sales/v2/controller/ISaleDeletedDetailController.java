package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntSaleDeletedDetail;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface ISaleDeletedDetailController {
    
    PojoIntSaleDeletedDetail addSaleDeletedDetail(PojoIntSaleDeletedDetail detail) throws BloSalesV2Exception;
    
}
