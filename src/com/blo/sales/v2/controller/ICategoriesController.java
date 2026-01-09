package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntCategory;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface ICategoriesController {

    PojoIntCategory registerCategory(PojoIntCategory category) throws BloSalesV2Exception;

}
