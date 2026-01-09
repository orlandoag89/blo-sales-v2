package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntCategory;
import com.blo.sales.v2.model.entities.CategoryEntity;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;

public class CategoryEntityMapper implements IToInner<CategoryEntity, PojoIntCategory>, IToOuter<CategoryEntity, PojoIntCategory> {

    @Override
    public CategoryEntity toInner(PojoIntCategory outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new CategoryEntity();
        inner.setCategory(outer.getCategory());
        inner.setDescription(outer.getDescription());
        return inner;
    }

    @Override
    public PojoIntCategory toOuter(CategoryEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoIntCategory();
        outer.setIdCategory(inner.getId_category());
        outer.setCategory(inner.getCategory());
        outer.setDescription(inner.getDescription());
        return outer;
    }
    
}
