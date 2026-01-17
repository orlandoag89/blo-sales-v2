package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.IDebtorsController;
import com.blo.sales.v2.controller.pojos.PojoIntDebtor;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntDebtors;
import com.blo.sales.v2.model.IDebtorsModel;
import com.blo.sales.v2.model.impl.DebtorsModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public class DebtorsControllerImpl implements IDebtorsController {
    
    private static DebtorsControllerImpl instance;
    
    private IDebtorsModel model;
    
    private DebtorsControllerImpl() {
        model = DebtorsModelImpl.getInstance();
    }
    
    public static DebtorsControllerImpl getInstance() {
        if (instance == null) {
            instance = new DebtorsControllerImpl();
        }
        return instance;
    }

    @Override
    public WrapperPojoIntDebtors getAllDebtors() throws BloSalesV2Exception {
        return model.getAllDebtors();
    }
    
    @Override
    public PojoIntDebtor saveDebtor(PojoIntDebtor debtor) throws BloSalesV2Exception {
        return model.saveDebtor(debtor);
    }

    @Override
    public PojoIntDebtor getDebtorById(long idDebtor) throws BloSalesV2Exception {
        return model.getDebtorById(idDebtor);
    }

    @Override
    public PojoIntDebtor updateDebtor(PojoIntDebtor debtor, long idDebtor) throws BloSalesV2Exception {
        return model.updateDebtor(debtor, idDebtor);
    }
    
}
