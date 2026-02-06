package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.ICashboxesActivesCostsController;
import com.blo.sales.v2.controller.pojos.PojoIntCashboxesActivesCosts;
import com.blo.sales.v2.model.ICashboxesActivesCostsModel;
import com.blo.sales.v2.model.impl.CashboxesActivesCostsModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.commons.GUILogger;

public class CashboxesActivesCostsControllerImpl implements ICashboxesActivesCostsController {
    
    private static final GUILogger logger = GUILogger.getLogger(CashboxesActivesCostsControllerImpl.class.getName());
    
    private static final ICashboxesActivesCostsModel model = CashboxesActivesCostsModelImpl.getInstance();
    
    private static CashboxesActivesCostsControllerImpl instance;
    
    private CashboxesActivesCostsControllerImpl() { }
    
    public static CashboxesActivesCostsControllerImpl getInstance() {
        if (instance == null) {
            instance = new CashboxesActivesCostsControllerImpl();
        }
        return instance;
    }

    @Override
    public PojoIntCashboxesActivesCosts addRelationship(PojoIntCashboxesActivesCosts data) throws BloSalesV2Exception {
        logger.log("agregando relacion");
        return model.addRelationship(data);
    }
    
}
