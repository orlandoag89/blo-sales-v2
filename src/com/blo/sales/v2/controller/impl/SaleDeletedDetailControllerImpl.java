package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.ISaleDeletedDetailController;
import com.blo.sales.v2.controller.pojos.PojoIntSaleDeletedDetail;
import com.blo.sales.v2.model.ISaleDeletedDetailModel;
import com.blo.sales.v2.model.impl.SaleDeletedDetailModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.commons.GUILogger;

public class SaleDeletedDetailControllerImpl implements ISaleDeletedDetailController {
    
    private static final GUILogger logger = GUILogger.getLogger(SaleDeletedDetailControllerImpl.class.getName());
    
    private static SaleDeletedDetailControllerImpl instance;
    
    private static final ISaleDeletedDetailModel model = SaleDeletedDetailModelImpl.getInstance();
    
    private SaleDeletedDetailControllerImpl() { }
    
    public static SaleDeletedDetailControllerImpl getInstance() {
        if (instance == null) {
            instance = new SaleDeletedDetailControllerImpl();
        }
        return instance;
    }

    @Override
    public PojoIntSaleDeletedDetail addSaleDeletedDetail(PojoIntSaleDeletedDetail detail) throws BloSalesV2Exception {
        logger.log(String.format("Guardando rzon de baja %s", detail.toString()));
        return model.addSaleDeletedDetail(detail);
    }
    
}
