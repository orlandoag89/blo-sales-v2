package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntSaleDeletedDetail;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface ISaleDeletedDetailModel {
    
    PojoIntSaleDeletedDetail addSaleDeletedDetail(PojoIntSaleDeletedDetail detail) throws BloSalesV2Exception;
    
}
