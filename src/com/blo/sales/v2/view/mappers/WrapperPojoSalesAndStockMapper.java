package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.WrapperPojoIntSalesAndStock;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.pojos.PojoSaleAndProduct;
import com.blo.sales.v2.view.pojos.WrapperPojoSalesAndStock;
import java.util.ArrayList;

public class WrapperPojoSalesAndStockMapper implements IToOuter<WrapperPojoIntSalesAndStock, WrapperPojoSalesAndStock> {

    private static PojoSaleAndProductMapper mapper = PojoSaleAndProductMapper.getInstance();
    
    private static WrapperPojoSalesAndStockMapper instance;
    
    private WrapperPojoSalesAndStockMapper() { }
    
    public static WrapperPojoSalesAndStockMapper getInstance() {
        if (instance == null) {
            instance = new WrapperPojoSalesAndStockMapper();
        }
        return instance;
    }
    @Override
    public WrapperPojoSalesAndStock toOuter(WrapperPojoIntSalesAndStock inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new WrapperPojoSalesAndStock();
        final var salesDetail = new ArrayList<PojoSaleAndProduct>();
        if (inner.getSalesDetail() != null && !inner.getSalesDetail().isEmpty()) {
            inner.getSalesDetail().forEach(s -> salesDetail.add(mapper.toOuter(s)));
        }
        outer.setSalesDetail(salesDetail);
        return outer;
    }
    
}
