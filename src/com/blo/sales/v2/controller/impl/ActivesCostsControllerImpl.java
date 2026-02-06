package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.IActivesCostsController;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntActivesCosts;
import com.blo.sales.v2.model.IActivesCostsModel;
import com.blo.sales.v2.model.impl.ActivesCostsModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.commons.GUILogger;

public class ActivesCostsControllerImpl implements IActivesCostsController {
    
    private static final GUILogger log = GUILogger.getLogger(ActivesCostsControllerImpl.class.getName());
    
    private static final IActivesCostsModel model = ActivesCostsModelImpl.getInstance();
    
    private static ActivesCostsControllerImpl instance;
    
    private ActivesCostsControllerImpl() { }
    
    public static ActivesCostsControllerImpl getInstance() {
        if (instance == null) {
            instance = new ActivesCostsControllerImpl();
        }
        return instance;
    }

    @Override
    public WrapperPojoIntActivesCosts addActiveCost(WrapperPojoIntActivesCosts activeCost) throws BloSalesV2Exception {
        log.log("guardando activos y costos " + activeCost.getActivesCosts().size());
        return model.addActiveCost(activeCost);
    }
    
}
