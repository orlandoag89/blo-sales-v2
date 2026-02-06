
package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntSale;
import com.blo.sales.v2.controller.pojos.enums.SalesStatusIntEnum;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.pojos.PojoSale;
import com.blo.sales.v2.view.pojos.enums.SalesStatusEnum;

public class PojoSaleMapper implements IToInner<PojoIntSale, PojoSale>, IToOuter<PojoIntSale, PojoSale> {
    
    private static PojoSaleMapper instance;
    
    private PojoSaleMapper() { }
    
    public static PojoSaleMapper getInstance() {
        if (instance == null) {
            instance = new PojoSaleMapper();
        }
        return instance;
    }

    @Override
    public PojoIntSale toInner(PojoSale outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new PojoIntSale();
        inner.setIdSale(outer.getIdSale());
        inner.setSaleStatus(SalesStatusIntEnum.valueOf(outer.getSaleStatus().name()));
        inner.setTotal(outer.getTotal());
        return inner;
    }

    @Override
    public PojoSale toOuter(PojoIntSale inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoSale();
        outer.setIdSale(outer.getIdSale());
        outer.setSaleStatus(SalesStatusEnum.valueOf(inner.getSaleStatus().name()));
        outer.setTotal(outer.getTotal());
        return outer;
    }
    
}
