package com.blo.sales.v2.view.windows.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntCategory;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.windows.pojos.PojoCategory;

public class CategoryMapper implements IToInner<PojoIntCategory, PojoCategory>, IToOuter<PojoIntCategory, PojoCategory> {

    @Override
    public PojoIntCategory toInner(PojoCategory outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new PojoIntCategory();
        inner.setCategory(outer.getCategory());
        inner.setDescription(outer.getDescription());
        return inner;
    }

    @Override
    public PojoCategory toOuter(PojoIntCategory inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoCategory();
        outer.setIdCategory(inner.getIdCategory());
        outer.setDescription(inner.getDescription());
        outer.setCategory(inner.getCategory());
        return outer;
    }
    
}
