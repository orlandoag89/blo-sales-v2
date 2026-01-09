package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntCategory;
import com.blo.sales.v2.model.entities.CategoriesWrapper;
import com.blo.sales.v2.model.entities.CategoryEntity;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import java.sql.SQLException;

public interface ICategoriesModel {
    
    PojoIntCategory registerCategory(PojoIntCategory category) throws SQLException, BloSalesV2Exception;
}
