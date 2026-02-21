package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.IHistoryController;
import com.blo.sales.v2.controller.pojos.PojoIntMovement;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntMovementsDetail;
import com.blo.sales.v2.model.IHistoryModel;
import com.blo.sales.v2.model.impl.HistoryModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public class HistoryControllerImpl implements IHistoryController {
    
    private static final IHistoryModel model = HistoryModelImpl.getInstance();
    
    private static HistoryControllerImpl instance;
    
    private HistoryControllerImpl() { }
    
    public static HistoryControllerImpl getInstance() {
        if (instance == null) {
            instance = new HistoryControllerImpl();
        }
        return instance;
    }

    @Override
    public PojoIntMovement registerMovement(PojoIntMovement movement) throws BloSalesV2Exception {
        return model.registerMovement(movement);
    }

    @Override
    public WrapperPojoIntMovementsDetail getHistoryFromProduct(long productId) throws BloSalesV2Exception {
        return model.getHistoryFromProduct(productId);
    }
    
}
