package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.IDebtorsController;
import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.ISalesController;
import com.blo.sales.v2.controller.pojos.PojoIntDebtor;
import com.blo.sales.v2.controller.pojos.PojoIntSaleProductData;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntDebtors;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntDebtorsDetails;
import com.blo.sales.v2.model.IDebtorsModel;
import com.blo.sales.v2.model.impl.DebtorsModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUILogger;
import java.math.BigDecimal;
import java.util.ArrayList;

public class DebtorsControllerImpl implements IDebtorsController {
    
    private static final GUILogger logger = GUILogger.getLogger(DebtorsControllerImpl.class.getName());
    
    private static final long ID_PRODUCT_PAY = 1;
    
    private static final IDebtorsModel model = DebtorsModelImpl.getInstance();
    
    private static final IProductsController productsController = ProductsControllerImpl.getInstance();
    
    private static final ISalesController salesController = SalesControllerImpl.getInstance();
    
    private static DebtorsControllerImpl instance;
    
    private DebtorsControllerImpl() { }
    
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
        logger.log("recuperando deudor " + idDebtor);
        return model.getDebtorById(idDebtor);
    }

    @Override
    public PojoIntDebtor updateDebtor(PojoIntDebtor debtor, long idDebtor) throws BloSalesV2Exception {
        return model.updateDebtor(debtor, idDebtor);
    }

    @Override
    public PojoIntDebtor addPayment(BigDecimal pay, long idUser, long idDebtor) throws BloSalesV2Exception {
        // validar que el deudor existe
        final var debtorFound = getDebtorById(idDebtor);
        BloSalesV2Utils.validateRule(debtorFound == null, "Deudor no encontrado");
        logger.log("deudor encontrado " + debtorFound.toString());
        // se registra como venta
        final var productPay = productsController.getProductById(ID_PRODUCT_PAY);
        final var productsLst = new ArrayList<PojoIntSaleProductData>();
        final var item = new PojoIntSaleProductData();
        item.setIdProduct(productPay.getIdProduct());
        item.setPrice(productPay.getPrice());
        item.setQuantityOnSale(BigDecimal.ZERO);
        productsLst.add(item);
        salesController.registerSale(pay, productsLst, idUser);
        // validar que el pago no cubre toda la deuda
        if (pay.compareTo(debtorFound.getDebt()) < 0) {
            logger.log("pago es menor que la deuda");
            final var amount = debtorFound.getDebt().subtract(pay);
            logger.log("nuevo monto " + amount);
            final var payments = BloSalesV2Utils.getPartialPayment(pay);
            debtorFound.setPayments(debtorFound.getPayments() + payments);
            debtorFound.setDebt(amount);
            return updateDebtor(debtorFound, idDebtor);
        }
        // pago completo
        logger.log("pago completo");
        debtorFound.setDebt(BigDecimal.ZERO);
        debtorFound.setPayments(BloSalesV2Utils.EMPTY_STRING);
        return updateDebtor(debtorFound, idDebtor);
    }

    @Override
    public WrapperPojoIntDebtorsDetails getDebtorsDetails() throws BloSalesV2Exception {
        return model.getDebtorsDetails();
    }

}
