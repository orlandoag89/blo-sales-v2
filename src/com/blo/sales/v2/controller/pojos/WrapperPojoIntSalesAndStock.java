package com.blo.sales.v2.controller.pojos;

import java.util.List;

public class WrapperPojoIntSalesAndStock {
    
    private List<PojoIntSaleAndProduct> salesDetail;

    public List<PojoIntSaleAndProduct> getSalesDetail() {
        return salesDetail;
    }

    public void setSalesDetail(List<PojoIntSaleAndProduct> salesDetail) {
        this.salesDetail = salesDetail;
    }
}
