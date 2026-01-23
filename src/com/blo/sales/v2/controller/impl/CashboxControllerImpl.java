package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.IActivesCostsController;
import com.blo.sales.v2.controller.ICashboxController;
import com.blo.sales.v2.controller.ICashboxesActivesCostsController;
import com.blo.sales.v2.controller.pojos.PojoIntCashbox;
import com.blo.sales.v2.controller.pojos.PojoIntCashboxesActivesCosts;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntActivesCosts;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntCashboxes;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntCashboxesDetails;
import com.blo.sales.v2.controller.pojos.enums.CashboxStatusIntEnum;
import com.blo.sales.v2.model.ICashboxModel;
import com.blo.sales.v2.model.impl.CashboxModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.commons.GUILogger;

public class CashboxControllerImpl implements ICashboxController {
    
    private static final GUILogger logger = GUILogger.getLogger(CashboxControllerImpl.class.getName());
    
    private static final ICashboxModel model = CashboxModelImpl.getInstance();
    
    private static final ICashboxesActivesCostsController cashboxesAactivesCostsController = CashboxesActivesCostsControllerImpl.getInstance();
    
    private static final IActivesCostsController activesCostsController = ActivesCostsControllerImpl.getInstance();
    
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

    @Override
    public PojoIntCashbox closeCashbox(PojoIntCashbox cashbox, WrapperPojoIntActivesCosts activesCosts) throws BloSalesV2Exception {
        cashbox.setStatus(CashboxStatusIntEnum.CLOSE);
        logger.log("cerrando caja " + cashbox.toString());
        updateCAshbox(cashbox, cashbox.getIdCashbox());
        logger.log("guardando costos " + activesCosts.getActivesCosts().size());
        if (activesCosts.getActivesCosts() != null && !activesCosts.getActivesCosts().isEmpty()) {
            final var saved = activesCostsController.addActiveCost(activesCosts);
            logger.log("se han guardado los activos y costos" + saved.getActivesCosts().size());
            // guardar las relaciones
            for (final var ac: saved.getActivesCosts()) {
                final var item = new PojoIntCashboxesActivesCosts();
                item.setFkActivesCosts(ac.getIdActiveCosts());
                item.setFkCashbox(cashbox.getIdCashbox());
                item.setTimestamp(cashbox.getTimestamp());
                final var relationSaved = cashboxesAactivesCostsController.addRelationship(item);
                logger.log("relacion guardada en la db " + relationSaved.toString());
            }
        }
        return cashbox;
    }

    @Override
    public WrapperPojoIntCashboxesDetails getCashboxesDetail() throws BloSalesV2Exception {
        logger.log("recuperando detalles de caja");
        return model.getCashboxesDetail();
    }

}
