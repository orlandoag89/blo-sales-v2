package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntPriceHistory;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IPricesHistoryModel {
    
    PojoIntPriceHistory addPriceHistory(PojoIntPriceHistory priceHistory) throws BloSalesV2Exception;
    
}
