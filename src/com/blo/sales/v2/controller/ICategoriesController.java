package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntCategory;
import com.blo.sales.v2.controller.pojos.WrapperIntPojoCategories;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface ICategoriesController {

    PojoIntCategory registerCategory(PojoIntCategory category) throws BloSalesV2Exception;
    
    WrapperIntPojoCategories getAllCategories() throws BloSalesV2Exception;
    
    PojoIntCategory updateCategory(int id, PojoIntCategory newData) throws BloSalesV2Exception;
    
    PojoIntCategory getCategoryById(int id) throws BloSalesV2Exception;

}
