package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.ICashboxController;
import com.blo.sales.v2.controller.IDebtorsController;
import com.blo.sales.v2.controller.IDebtorsSalesController;
import com.blo.sales.v2.controller.IHistoryController;
import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.ISaleDeletedDetailController;
import com.blo.sales.v2.controller.ISalesController;
import com.blo.sales.v2.controller.ISalesProductController;
import com.blo.sales.v2.controller.IUserController;
import com.blo.sales.v2.controller.pojos.PojoIntCashbox;
import com.blo.sales.v2.controller.pojos.PojoIntDebtor;
import com.blo.sales.v2.controller.pojos.PojoIntDebtorSale;
import com.blo.sales.v2.controller.pojos.PojoIntMovement;
import com.blo.sales.v2.controller.pojos.PojoIntProduct;
import com.blo.sales.v2.controller.pojos.PojoIntSale;
import com.blo.sales.v2.controller.pojos.PojoIntSaleDeletedDetail;
import com.blo.sales.v2.controller.pojos.PojoIntSaleProduct;
import com.blo.sales.v2.controller.pojos.PojoIntSaleProductData;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntSales;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntSalesAndStock;
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
import com.blo.sales.v2.view.commons.GUILogger;
import java.math.BigDecimal;
import java.util.List;

public class SalesControllerImpl implements ISalesController {
    
    private static final GUILogger logger = GUILogger.getLogger(SalesControllerImpl.class.getName());
    
    private static SalesControllerImpl instance;
    
    private static final ISalesModel saleModel = SalesModelImpl.getInstance();
    
    private static final IUserController userController = UserControllerImpl.getInstance();
    
    private static final IHistoryController historyController = HistoryControllerImpl.getInstance();
    
    private static final IProductsController productsController = ProductsControllerImpl.getInstance();
    
    private static final ISalesProductController salesProductsController = SalesProductControllerImpl.getInstance();
    
    private static final ICashboxController cashboxController = CashboxControllerImpl.getInstance();
    
    private static final IDebtorsController debtorsController = DebtorsControllerImpl.getInstance();
    
    private static final IDebtorsSalesController debtorsSalesController = DebtorsSalesControllerImpl.getInstance();
    
    private static final ISaleDeletedDetailController salesDeletedController = SaleDeletedDetailControllerImpl.getInstance();
    
    private SalesControllerImpl() { }
    
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
            BloSalesV2Utils.validateRule(
                    productFound == null,
                    BloSalesV2Utils.CODE_PRODUCT_NOT_FOUND,
                    productFound.getProduct() + BloSalesV2Utils.PRODUCT_NOT_FOUND
            );
            // valida que el exista suficiente cantidad de producto
            BloSalesV2Utils.validateRule(
                    productFound.getQuantity().compareTo(product.getQuantityOnSale()) < 0,
                    BloSalesV2Utils.CODE_PRODUCT_INSUFFICIENT,
                    productFound.getProduct() + BloSalesV2Utils.PRODUCT_INSUFFICIENT
            );
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
            saleProduct.setProductTotalOnSale(p.getProductBuyTotal());
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
        registereRelationship(debtor.getIdDebtor(), sale.getIdSale(), sale.getTimestamp());
        return debtor;
    }
    
    @Override
    public PojoIntDebtor registerSaleWithDebtor(
            BigDecimal totalSale,
            List<PojoIntSaleProductData> productsInfo,
            BigDecimal partialPay,
            String partialPayments,
            long idUser,
            long idDebtor
    ) throws BloSalesV2Exception {
        /** validaciones */
        final var debtorFound = debtorsController.getDebtorById(idDebtor);
        // se guarda deuda original
        BloSalesV2Utils.validateRule(debtorFound == null, BloSalesV2Utils.CODE_DEBTOR_NOT_FOUND, BloSalesV2Utils.DEBTOR_NOT_FOUND);
        debtorFound.setDebt(totalSale);
        /** se actualiza deudor */
        if (partialPay.compareTo(BigDecimal.ZERO) == 0) {
            // el deudor no ha abonado
            logger.log("sin pago parcial");
            // se guarda relacion
            final var resiteredSale = registerSale(BigDecimal.ZERO, productsInfo, idUser);
            registereRelationship(idDebtor, resiteredSale.getIdSale(), resiteredSale.getTimestamp());
            return debtorsController.updateDebtor(debtorFound, idDebtor);
        }
        // el deudor ha abonado algo
        logger.log("abono de deudor " + partialPay);
        // flujo cuando no se cubre deuda completa
        if (partialPay.compareTo(debtorFound.getDebt()) < 0) {
            logger.log("el deudor no pago completo");
            registerSale(partialPay, productsInfo, idUser);
            debtorFound.setPayments(partialPayments);
            logger.log("debtor found actualizado " + debtorFound.toString());
            // se guarda relacion
            final var resiteredSale = registerSale(BigDecimal.ZERO, productsInfo, idUser);
            registereRelationship(idDebtor, resiteredSale.getIdSale(), resiteredSale.getTimestamp());
            return debtorsController.updateDebtor(debtorFound, idDebtor);
        }
        // se cubre deuda completa
        logger.log("se cubre deuda completa");
        // validar que no se guarden numeros negativos en la deuda
        final var registeredSale = registerSale(totalSale, productsInfo, idUser);
        debtorsSalesController.deleteRelationhip(registeredSale.getIdSale());
        debtorFound.setPayments(BloSalesV2Utils.EMPTY_STRING);
        debtorFound.setDebt(BigDecimal.ZERO);
        return debtorsController.updateDebtor(debtorFound, idDebtor);
    }
    
    @Override
    public WrapperPojoIntSalesAndStock retrieveAllSalesDetail() throws BloSalesV2Exception {
        logger.log("recuperando todas las ventas");
        return saleModel.retrieveAllSalesDetail();
    }
    
    @Override
    public WrapperPojoIntSalesAndStock retrieveSalesByStatus(SalesStatusIntEnum saleStatus) throws BloSalesV2Exception {
        logger.log("recuperando ventas por status " + saleStatus.name());
        return saleModel.retrieveSalesByStatus(saleStatus);
    }

    @Override
    public WrapperPojoIntSales retrieveSalesDataByStatus(SalesStatusIntEnum saleStatus) throws BloSalesV2Exception {
        logger.log("recuperando ventas por " + saleStatus.name());
        return saleModel.retrieveSalesDataByStatus(saleStatus);
    }

    @Override
    public boolean setCashboxSale(long idSale) throws BloSalesV2Exception {
        logger.log("acualizando la venta " + idSale);
        return saleModel.setCashboxSale(idSale);
    }
    
    @Override
    public PojoIntSaleDeletedDetail deleteSaleProduct(long idUser, long idSale, long idProduct, String reason) throws BloSalesV2Exception {
        final var output = new PojoIntSaleDeletedDetail();
        // validar que existe la relacion
        final var relationFound = salesProductsController.getRelationship(idSale, idProduct);
        BloSalesV2Utils.validateRule(relationFound == null, BloSalesV2Utils.CODE_SALES_PRODUCT_NOT_FOUND, BloSalesV2Utils.SALES_PRODUCT_NOT_FOUND);
        // validar producto
        final var productFound = productsController.getProductById(relationFound.getFkProduct());
        BloSalesV2Utils.validateRule(productFound == null, BloSalesV2Utils.CODE_PRODUCT_NOT_FOUND, BloSalesV2Utils.ERROR_PRODUCT_NOT_FOUND);
        final var timestamp = BloSalesV2Utils.getTimestamp();
        // agregar el producto al stock
        var productQuantityOnSale = relationFound.getQuantityOnSale();
        productQuantityOnSale = productFound.getQuantity().add(productQuantityOnSale);
        productFound.setQuantity(productQuantityOnSale);
        logger.log(String.format("Product data [%s]", productFound.toString()));
        productsController.updateProductInfo(productFound, ReasonsIntEnum.DEVOLUTION, idUser, TypesIntEnum.INPUT);
        // restar el precio del producto a la venta
        var totalOnSale = relationFound.getTotalOnSale();
        totalOnSale = totalOnSale.subtract(productFound.getPrice());
        relationFound.setTotalOnSale(totalOnSale);
        relationFound.setIsLive(false);
        relationFound.setTimestap(timestamp);
        relationFound.setProductTotalOnSale(BigDecimal.ZERO);
        relationFound.setQuantityOnSale(BigDecimal.ZERO);
        logger.log(String.format("guardando datos [%s]", relationFound.toString()));
        salesProductsController.updateRelationship(relationFound);
        // restar el dinero de la venta a la caja
        final var currentCashbox = cashboxController.getOpenCashbox();
        BloSalesV2Utils.validateRule(currentCashbox == null, BloSalesV2Utils.CODE_CASHBOX_NOT_DEVOLUTION, BloSalesV2Utils.ERROR_CASHBOX_NOT_DEVOLUTION);
        var currentTotal = currentCashbox.getAmount().subtract(productFound.getPrice());
        currentCashbox.setAmount(currentTotal);
        currentCashbox.setTimestamp(timestamp);
        logger.log(String.format("datos de la caja actualizar %s", currentCashbox.toString()));
        cashboxController.updateCAshbox(currentCashbox, currentCashbox.getIdCashbox());
        output.setFkSaleProduct(relationFound.getIdSaleProduct());
        output.setFkUser(idUser);
        output.setReason(reason);
        output.setTimestamp(timestamp);
        return salesDeletedController.addSaleDeletedDetail(output);
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
    
    /**
     * registra la relacion deudor-venta
     * @param idDebtor
     * @param idSale
     * @param timestamp
     * @return
     * @throws BloSalesV2Exception 
     */
    private PojoIntDebtorSale registereRelationship(long idDebtor, long idSale, String timestamp) throws BloSalesV2Exception {
        final var debtorSale = new PojoIntDebtorSale();
        debtorSale.setFkDebtor(idDebtor);
        debtorSale.setFkSale(idSale);
        debtorSale.setTimestamp(timestamp);
        return debtorsSalesController.addRelationship(debtorSale);
    }

}
