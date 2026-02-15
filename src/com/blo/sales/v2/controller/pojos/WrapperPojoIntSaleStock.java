package com.blo.sales.v2.controller.pojos;

import java.util.List;

public class WrapperPojoIntSaleStock {
    
    private List<PojoIntSaleProduct> salesStock;

    public List<PojoIntSaleProduct> getSalesStock() {
        return salesStock;
    }

    public void setSalesStocks(List<PojoIntSaleProduct> salesStock) {
        this.salesStock = salesStock;
    }
    
}
