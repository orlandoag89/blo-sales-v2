package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.ICashboxController;
import com.blo.sales.v2.controller.pojos.PojoIntCashbox;
import com.blo.sales.v2.model.ICashboxModel;
import com.blo.sales.v2.model.impl.CashboxModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public class CashboxControllerImpl implements ICashboxController {
    
    private static CashboxControllerImpl instance;
    
    private ICashboxModel model;
    
    private CashboxControllerImpl() {
        model = CashboxModelImpl.getInstance();
    }
    
    public static CashboxControllerImpl getInstance() {
        if (instance == null) {
            instance = new CashboxControllerImpl();
        }
        return instance;
    }

    @Override
    public PojoIntCashbox addCashbox(PojoIntCashbox cashbox) throws BloSalesV2Exception {
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
    
}
