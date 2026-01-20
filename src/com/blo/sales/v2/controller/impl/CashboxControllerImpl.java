package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.ICashboxController;
import com.blo.sales.v2.controller.pojos.PojoIntCashbox;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntCashboxes;
import com.blo.sales.v2.model.ICashboxModel;
import com.blo.sales.v2.model.impl.CashboxModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.commons.GUILogger;

public class CashboxControllerImpl implements ICashboxController {
    
    private static final GUILogger logger = GUILogger.getLogger(CashboxControllerImpl.class.getName());
    
    private ICashboxModel model = CashboxModelImpl.getInstance();
    
    private static CashboxControllerImpl instance;
        
    private CashboxControllerImpl() { }
    
    public static CashboxControllerImpl getInstance() {
        if (instance == null) {
            instance = new CashboxControllerImpl();
        }
        return instance;
    }

    @Override
    public PojoIntCashbox addCashbox(PojoIntCashbox cashbox) throws BloSalesV2Exception {
        logger.log("creando caja de dinero");
        return model.addCashbox(cashbox);
    }

    @Override
    public PojoIntCashbox getOpenCashbox() throws BloSalesV2Exception {
        return model.getOpenCashbox();
    }

    @Override
    public PojoIntCashbox updateCAshbox(PojoIntCashbox cashbox, long idCashbox) throws BloSalesV2Exception {
        return model.updateCashbox(cashbox, idCashbox);
    }
    
    @Override
    public WrapperPojoIntCashboxes getAllCashboxes() throws BloSalesV2Exception {
        return model.getAllCashboxes();
    }
}
