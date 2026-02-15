package com.blo.sales.v2.model.entities;

import java.util.List;

public class WrapperSaleStockEntity {
    
    private List<SaleProductEntity> salesStock;

    public List<SaleProductEntity> getSalesStock() {
        return salesStock;
    }

    public void setSalesStock(List<SaleProductEntity> salesStock) {
        this.salesStock = salesStock;
    }
    
}
