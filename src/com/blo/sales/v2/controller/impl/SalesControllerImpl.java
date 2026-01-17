package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.ICashboxController;
import com.blo.sales.v2.controller.IDebtorsController;
import com.blo.sales.v2.controller.IDebtorsSalesController;
import com.blo.sales.v2.controller.IHistoryController;
import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.ISalesController;
import com.blo.sales.v2.controller.ISalesProductController;
import com.blo.sales.v2.controller.IUserController;
import com.blo.sales.v2.controller.pojos.PojoIntCashbox;
import com.blo.sales.v2.controller.pojos.PojoIntDebtor;
import com.blo.sales.v2.controller.pojos.PojoIntDebtorSale;
import com.blo.sales.v2.controller.pojos.PojoIntMovement;
import com.blo.sales.v2.controller.pojos.PojoIntProduct;
import com.blo.sales.v2.controller.pojos.PojoIntSale;
import com.blo.sales.v2.controller.pojos.PojoIntSaleProduct;
import com.blo.sales.v2.controller.pojos.PojoIntSaleProductData;
import com.blo.sales.v2.controller.pojos.enums.CashboxStatusIntEnum;
import com.blo.sales.v2.controller.pojos.enums.ReasonsIntEnum;
import com.blo.sales.v2.controller.pojos.enums.SalesStatusIntEnum;
import com.blo.sales.v2.controller.pojos.enums.TypesIntEnum;
import com.blo.sales.v2.model.ISalesModel;
import com.blo.sales.v2.model.entities.enums.ReasonsEntityEnum;
import com.blo.sales.v2.model.entities.enums.TypesEntityEnum;
import com.blo.sales.v2.model.impl.SalesModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import java.math.BigDecimal;
import java.util.List;

/**
 * validar flujo para caja de dinero 
 */
public class SalesControllerImpl implements ISalesController {
    
    private static SalesControllerImpl instance;
    
    private ISalesModel saleModel;
    
    private IUserController userController;
    
    private IHistoryController historyController;
    
    private IProductsController productsController;
    
    private ISalesProductController salesProductsController;
    
    private ICashboxController cashboxController;
    
    private IDebtorsController debtorsController;
    
    private IDebtorsSalesController debtorsSalesController;
    
    private SalesControllerImpl() {
        saleModel = SalesModelImpl.getInstance();
        historyController = HistoryControllerImpl.getInstance();
        userController = UserControllerImpl.getInstance();
        productsController = ProductsControllerImpl.getInstance();
        salesProductsController = SalesProductControllerImpl.getInstance();
        cashboxController = CashboxControllerImpl.getInstance();
        debtorsController = DebtorsControllerImpl.getInstance();
        debtorsSalesController = DebtorsSalesControllerImpl.getInstance();
    }
    
    public static SalesControllerImpl getInstance() {
        if (instance == null) {
            instance = new SalesControllerImpl();
        }
        return instance;
    }

    @Override
    public PojoIntSale registerSale(BigDecimal totalSale, List<PojoIntSaleProductData> products, long idUser) throws BloSalesV2Exception {
        /** validaciones */
        final var productsFound = productsController.getAllProducts().getProducts();
        for (final var product: products) {
            final var productFound = filterProductById(productsFound, product.getIdProduct());
            // validar que el producto de entrada exista en el stock
            BloSalesV2Utils.validateRule(productFound == null, "El product " + productFound.getProduct() + " no existe");
            // valida que el exista suficiente cantidad de producto
            BloSalesV2Utils.validateRule(productFound.getQuantity().compareTo(product.getQuantityOnSale()) < 0, "No ha suficientes productos de " + productFound.getProduct());
        }
        userController.getUserById(idUser);
        final var timestamp = BloSalesV2Utils.getTimestamp();
        // registro de venta
        final var sale = new PojoIntSale();
        sale.setSaleStatus(SalesStatusIntEnum.CLOSE);
        sale.setTotal(totalSale);
        sale.setTimestamp(timestamp);
        final var saleSaved = saleModel.registerSale(sale);
        for (final var p: products) {
            final var productFound = filterProductById(productsFound, p.getIdProduct());
            // registro de movimiento
            final var movement = new PojoIntMovement();
            movement.setFk_product(p.getIdProduct());
            movement.setFk_user(idUser);
            movement.setQuantity(p.getQuantityOnSale());
            movement.setReason(ReasonsEntityEnum.SALE);
            movement.setTimestamp(timestamp);
            movement.setType(TypesEntityEnum.OUTPUT);
            historyController.registerMovement(movement);
            // registro de movimiento en venta
            final var saleProduct = new PojoIntSaleProduct();
            saleProduct.setFkProduct(p.getIdProduct());
            saleProduct.setFkSale(saleSaved.getIdSale());
            saleProduct.setQuantityOnSale(p.getQuantityOnSale());
            saleProduct.setTimestap(timestamp);
            saleProduct.setTotalOnSale(totalSale);
            // guardar relacion venta-product
            salesProductsController.addSalesProduct(saleProduct);
            // actualizar cantidad en el stock
            final var newQuantity = productFound.getQuantity().subtract(p.getQuantityOnSale());
            productFound.setQuantity(newQuantity);
            productsController.updateProductInfo(productFound, ReasonsIntEnum.SALE, idUser, TypesIntEnum.OUTPUT);
        }
        /** se agrega el dinero a la caja */
        // recupera caja abierta
        var openCashbox = cashboxController.getOpenCashbox();
        // si no existe se crea
        if (openCashbox == null) {
            final var newCashbox = new PojoIntCashbox();
            newCashbox.setFkUser(idUser);
            newCashbox.setAmount(BigDecimal.ZERO);
            newCashbox.setStatus(CashboxStatusIntEnum.OPEN);
            newCashbox.setTimestamp(timestamp);
            openCashbox = cashboxController.addCashbox(newCashbox);
        }
        // se suma la cantidad de la venta al monto de la caja abierta
        final var amount = openCashbox.getAmount().add(totalSale);
        openCashbox.setAmount(amount);
        openCashbox.setTimestamp(timestamp);
        // actualizar cantidad en la caja
        cashboxController.updateCAshbox(openCashbox, openCashbox.getIdCashbox());
        return saleSaved;
    }
    
    @Override
    public PojoIntDebtor registerSaleWithNewDebtor(
            BigDecimal totalSale,
            List<PojoIntSaleProductData> productsInfo,
            long idUser,
            PojoIntDebtor debtorData
    ) throws BloSalesV2Exception {
        /** se realiza la venta */
        final var sale = registerSale(totalSale, productsInfo, idUser);
        /** se registra el deudor */
        final var debtor = debtorsController.saveDebtor(debtorData);
        /** guardar relacion deudor-venta */
        final var debtorSale = new PojoIntDebtorSale();
        debtorSale.setFkDebtor(debtor.getIdDebtor());
        debtorSale.setFkSale(sale.getIdSale());
        debtorSale.setTimestamp(sale.getTimestamp());
        debtorsSalesController.addRelationship(debtorSale);
        return debtor;
    }
    
    @Override
    public PojoIntDebtor registerSaleWithDebtor(
            BigDecimal totalSale,
            List<PojoIntSaleProductData> productsInfo,
            String partialPay,
            long idUser,
            long idDebtor
    ) throws BloSalesV2Exception {
        /** validaciones */
        final var debtorFound = debtorsController.getDebtorById(idDebtor);
        BloSalesV2Utils.validateRule(debtorFound == null, "Deudor no encontrado");
        /** se actualiza deudor */
        if (partialPay.isBlank()) {
            // el deudor no ha abonado
            registerSale(BigDecimal.ZERO, productsInfo, idUser);
            final var amount = debtorFound.getTotal().add(totalSale);
            debtorFound.setTotal(amount);
            return debtorsController.updateDebtor(debtorFound, idDebtor);
        }
        // el deudor ha abonado algo
        final var payment = new BigDecimal(partialPay);
        // se regitra venta
        final var sale = registerSale(payment, productsInfo, idUser);
        final var amount = debtorFound.getTotal().subtract(payment);
        // se cubrio toda la deuda
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            // se eliminan pagos abonos
            debtorFound.setPayments(BloSalesV2Utils.EMPTY_STRING);
            // monto igual a 0
            debtorFound.setTotal(BigDecimal.ZERO);
            return debtorsController.updateDebtor(debtorFound, idDebtor);
        }
        // se actualiza el total y se agrega un pago parcial
        final var partiaPayment = BloSalesV2Utils.SEPARATOR_PAYMENTS + partialPay + "-" + sale.getTimestamp();
        debtorFound.setTotal(amount);
        debtorFound.setPayments(debtorFound.getPayments() + partiaPayment);
        return debtorsController.updateDebtor(debtorFound, idDebtor);
    }
    
    /**
     * filtra un producto o regresa null
     * @param products registros de la base de datos
     * @param idProduct id a buscar
     * @return 
     */
    private PojoIntProduct filterProductById(List<PojoIntProduct> products, long idProduct) {
        return products.stream().filter(item -> item.getIdProduct() == idProduct).findAny().orElse(null);
        
    }
    
}
