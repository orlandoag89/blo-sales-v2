package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntPriceHistory;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IPricesHistoryController {
    
    PojoIntPriceHistory addPriceHistory(PojoIntPriceHistory priceHistory) throws BloSalesV2Exception;
    
}
