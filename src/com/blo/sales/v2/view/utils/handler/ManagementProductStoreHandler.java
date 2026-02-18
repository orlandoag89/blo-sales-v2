package com.blo.sales.v2.view.utils.handler;

import com.blo.sales.v2.view.pojos.PojoProduct;
import java.math.BigDecimal;
import java.util.function.BiConsumer;

public enum ManagementProductStoreHandler {
    
    /**
     * Recibo dos cosas (el Pojo y el Valor) y ejecuto una acción sin devolver nada
     */
    PRODUCT((p, v) -> p.setProduct((String) v)),
    NAME((p, v) -> p.setProduct((String) v)),
    QUANTITY((p, v) -> p.setQuantity(new BigDecimal(v.toString()))),
    COST_OF_SALE((p, v) -> p.setCostOfSale(new BigDecimal(v.toString()))),
    PRICE((p, v) -> p.setPrice(new BigDecimal(v.toString()))),
    KG((p, v) -> p.setKg((Boolean) v)),
    BAR_CODE((p, v) -> p.setBarCode((String) v)),
    FK_CATEGORY((p, v) -> p.setFkCategory((Long) v));
    
    private final BiConsumer<PojoProduct, Object> setter;
    
    ManagementProductStoreHandler(BiConsumer<PojoProduct, Object> setter) {
        this.setter = setter;
    }
    
    public void apply(PojoProduct product, Object value) {
        setter.accept(product, value);
    }
    
}
