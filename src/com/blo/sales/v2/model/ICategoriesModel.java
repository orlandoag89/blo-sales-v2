package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntCategory;
import com.blo.sales.v2.controller.pojos.WrapperIntPojoCategories;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import java.sql.SQLException;

public interface ICategoriesModel {
    
    PojoIntCategory registerCategory(PojoIntCategory category) throws SQLException, BloSalesV2Exception;
    
    WrapperIntPojoCategories getAllCategories() throws BloSalesV2Exception;
    
    PojoIntCategory updateCategory(int id, PojoIntCategory newData) throws BloSalesV2Exception;
    
    PojoIntCategory getCategoryById(int id) throws BloSalesV2Exception;
    
}
