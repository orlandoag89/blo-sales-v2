package com.blo.sales.v2.model.entities;

import java.util.List;

public class WrapperSalesAndStockEntity {
    
    private List<SaleAndProductEntity> salesDetail;

    public List<SaleAndProductEntity> getSalesDetail() {
        return salesDetail;
    }

    public void setSalesDetail(List<SaleAndProductEntity> salesDetail) {
        this.salesDetail = salesDetail;
    }
    
}
