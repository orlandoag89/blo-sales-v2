package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.IDebtorsController;
import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.ISalesController;
import com.blo.sales.v2.controller.impl.DebtorsControllerImpl;
import com.blo.sales.v2.controller.impl.ProductsControllerImpl;
import com.blo.sales.v2.controller.impl.SalesControllerImpl;
import com.blo.sales.v2.controller.pojos.PojoIntSaleProductData;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.utils.BloSalesV2UtilsEnum;
import com.blo.sales.v2.view.commons.CommonAlerts;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.commons.GUILogger;
import com.blo.sales.v2.view.dialogs.DebtorsDialog;
import com.blo.sales.v2.view.dialogs.SelectorDialog;
import com.blo.sales.v2.view.mappers.DebtorMapper;
import com.blo.sales.v2.view.mappers.PojoSaleProductDataMapper;
import com.blo.sales.v2.view.mappers.WrapperDebtorsMapper;
import com.blo.sales.v2.view.mappers.WrapperPojoProductsMapper;
import com.blo.sales.v2.view.pojos.PojoLoggedInUser;
import com.blo.sales.v2.view.pojos.PojoProduct;
import com.blo.sales.v2.view.pojos.PojoSaleProductData;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;

public class Sales extends javax.swing.JPanel {
    
    private static final GUILogger logger = GUILogger.getLogger(Sales.class.getName());
    
    private static final IProductsController productsController = ProductsControllerImpl.getInstance();
    
    private static final WrapperPojoProductsMapper mapperProducts = WrapperPojoProductsMapper.getInstance();
    
    private static final ISalesController salesController = SalesControllerImpl.getInstance();
    
    private static final IDebtorsController debtorsController = DebtorsControllerImpl.getInstance();
    
    private static final PojoSaleProductDataMapper saleProductMapper = PojoSaleProductDataMapper.getInstance();
    
    private static final WrapperDebtorsMapper wrapperDebtorsMapper = WrapperDebtorsMapper.getInstance();
    
    private static final DebtorMapper debtorMapper = DebtorMapper.getInstance();
    
    private List<PojoProduct> products;

    private BigDecimal totalSale;
    
    private PojoProduct productFound;
    
    private PojoLoggedInUser userData;
        
    public Sales(PojoLoggedInUser userData) {
        this.userData = userData;
        totalSale = BigDecimal.ZERO;
        initComponents();
        resetFields();
        disableButtons();
        try {
            retrieveProducts();
            final String[] titles = {"ID", "Producto", "Cantidad comprada", "Precio", "Total"};
            GUICommons.loadTitleOnTable(tblProductsSales, titles, false);
            GUICommons.addDoubleClickOnTable(tblProductsSales, id -> {
                final var model = (DefaultTableModel) tblProductsSales.getModel();
                // elimina un item de la lista
                final var indexSelected = tblProductsSales.getSelectedRow();
                if (indexSelected != -1) {
                    final var filaModelo = tblProductsSales.convertRowIndexToModel(indexSelected);
                    final var price = model.getValueAt(filaModelo, 4).toString();
                    totalSale = totalSale.subtract(new BigDecimal(price));
                    model.removeRow(indexSelected);
                    GUICommons.setTextToField(lblTotal, "Total: $" + totalSale);
                    if (totalSale.compareTo(BigDecimal.ZERO) == 0) {
                        disableButtons();
                    }
                }
            });
        } catch (BloSalesV2Exception ex) {
            logger.error(ex.getMessage());
            CommonAlerts.openError(ex.getMessage());
        }
        txtSearch.requestFocusInWindow();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTotal = new javax.swing.JLabel();
        pnlPay = new javax.swing.JPanel();
        btnComplete = new javax.swing.JButton();
        btnDebtors = new javax.swing.JButton();
        pnlSearch = new javax.swing.JPanel();
        lblQuantity = new javax.swing.JLabel();
        nmbQuantity = new javax.swing.JTextField();
        txtSearch = new javax.swing.JTextField();
        lblBarCode = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductsSales = new javax.swing.JTable();

        btnComplete.setText("Completo");
        btnComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteActionPerformed(evt);
            }
        });

        btnDebtors.setText("Incompleto");
        btnDebtors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDebtorsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPayLayout = new javax.swing.GroupLayout(pnlPay);
        pnlPay.setLayout(pnlPayLayout);
        pnlPayLayout.setHorizontalGroup(
            pnlPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPayLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnComplete)
                .addGap(18, 18, 18)
                .addComponent(btnDebtors)
                .addContainerGap())
        );
        pnlPayLayout.setVerticalGroup(
            pnlPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnComplete)
                    .addComponent(btnDebtors))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        lblQuantity.setText("Cantidad");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        lblBarCode.setText("Código de barras / nombre (F3)");

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nmbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQuantity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addComponent(lblBarCode)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtSearch))
                .addGap(6, 6, 6))
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBarCode)
                    .addComponent(lblQuantity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nmbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblProductsSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblProductsSales);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1288, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (evt.getKeyCode() == GUICommons.ENTER_KEY_CODE) {
            try {
                final var termToSearch = GUICommons.getTextFromField(txtSearch, true);
                /** busqueda por codigo de barras */
                if (BloSalesV2Utils.validateTextWithPattern(BloSalesV2Utils.ONLY_NUMBERS, termToSearch)) {
                    filterProduct(termToSearch, true);
                    addItemToList();
                }
            } catch (BloSalesV2Exception ex) {
                logger.error(ex.getMessage());
                CommonAlerts.openError(ex.getMessage());
            }
            
        }
        if (evt.getKeyCode() == GUICommons.F3_SEARCH) {
            final var productsString = products.stream()
                .map(item -> item.toString())
                .collect(Collectors.toList());
               /** abre un cuadro de dialogo */
               final var dialog = new SelectorDialog<>(
                    this,
                    "Selecciona un busqueda manual de producto",
                    productsString,
                    item -> {
                        filterProduct(item, false);
                        GUICommons.setTextToField(txtSearch, productFound.getProduct());
                        addItemToList();
                    });
               dialog.setVisible(true);
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    /** ajustar para reiniciar lista */
    private void btnCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteActionPerformed
        try {
            salesController.registerSale(totalSale, getProductData(), this.userData.getIdUser());
            disableButtons();
            GUICommons.setTextToField(lblTotal, "Total: 0");
            totalSale = BigDecimal.ZERO;
            resetFields();
        } catch (BloSalesV2Exception ex) {
            logger.error(ex.getMessage());
            CommonAlerts.openError(ex.getMessage());
        }
    }//GEN-LAST:event_btnCompleteActionPerformed

    private void btnDebtorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDebtorsActionPerformed
        try {
            final var debtors = wrapperDebtorsMapper.toOuter(debtorsController.getAllDebtors());
            final var debtorsCoopy = wrapperDebtorsMapper.toOuter(debtorsController.getAllDebtors());
            final var debtorsDialog = new DebtorsDialog<>(
                this,
                "Deudores",
                debtors.getDebtors(),
                totalSale,
                item -> {
                    try {
                        // formato de pagos amountTIMESTAMPtimestamp, 
                        logger.log("deudor " + item.toString());
                        var pay = BloSalesV2Utils.getFirstLastPayment(item.getPayments(), BloSalesV2UtilsEnum.LAST);
                        // es nuevo deudor  
                        if (item.getIdDebtor() == 0) {
                            salesController.registerSaleWithNewDebtor(
                                pay,
                                getProductData(),
                                userData.getIdUser(),
                                debtorMapper.toInner(item)
                            );
                        } else {
                            // valida que no se haya hecho un pago
                            final var debtorFound = debtorsCoopy.getDebtors().stream().
                                    filter(d -> d.getIdDebtor() == item.getIdDebtor()).
                                    findFirst().
                                    orElse(null);
                            if (debtorFound.getPayments().equals(item.getPayments())) {
                                pay = BigDecimal.ZERO;
                            }
                            salesController.registerSaleWithDebtor(
                                item.getDebt(),
                                getProductData(),
                                pay,
                                item.getPayments(),
                                userData.getIdUser(),
                                item.getIdDebtor());
                        }
                        resetFields();
                        totalSale = BigDecimal.ZERO;
                    } catch (BloSalesV2Exception ex) {
                        logger.error(ex.getMessage());
                        CommonAlerts.openError(ex.getMessage());
                    }
                });
            debtorsDialog.setVisible(true);
        } catch (BloSalesV2Exception ex) {
            logger.error(ex.getMessage());
            CommonAlerts.openError(ex.getMessage());
        }
    }//GEN-LAST:event_btnDebtorsActionPerformed
    
    private void addItemToList() {
        try {
            BloSalesV2Utils.validateRule(productFound == null, BloSalesV2Utils.CODE_PRODUCT_NOT_SELECTED, BloSalesV2Utils.PRODUCT_NOT_SELECTED);
            
            final var quantity = GUICommons.getTextFromField(nmbQuantity, true);
            // valida si se puede con pesos solamente si el producto se vende por kg
            var onSaleQuantity = new BigDecimal(BigInteger.ZERO);
            var onSalePrice = new BigDecimal(BigInteger.ZERO);
            if (quantity.toLowerCase().startsWith("p") && productFound.isKg()) {
                // Extraemos el valor numérico después de la 'P'
                final var cash = new BigDecimal(quantity.substring(1));
                final var price = productFound.getPrice();
                // 1. Calculamos la fracción de kilo (10 / 154)
                // 2. Multiplicamos por 1000 para pasar a gramos
                // 3. Redondeamos al final a 2 decimales
                onSaleQuantity = cash.divide(price, 6, RoundingMode.HALF_UP)
                    //.multiply(new BigDecimal("1000"))
                    .setScale(3, RoundingMode.HALF_UP);
                totalSale = totalSale.add(cash);
                onSalePrice = cash;
            } else {
                // Si no empieza con P, se asume que la cantidad ya es numérica (gramos o piezas)
                onSaleQuantity = new BigDecimal(quantity);
                onSalePrice = productFound.getPrice().multiply(onSaleQuantity);
                
                if (productFound.isKg()) {
                    onSalePrice = onSalePrice.setScale(2, RoundingMode.HALF_UP);
                }
                totalSale = totalSale.add(onSalePrice);
            }
            final var model = (DefaultTableModel) tblProductsSales.getModel();
            final Object[] productInfoData = {
                productFound.getIdProduct(),
                productFound.getProduct(),
                onSaleQuantity,
                productFound.getPrice(),
                onSalePrice
            };
            model.addRow(productInfoData);
            GUICommons.setTextToField(txtSearch, BloSalesV2Utils.EMPTY_STRING);
            GUICommons.setTextToField(lblTotal, "Total: $" + totalSale);
            GUICommons.setTextToField(nmbQuantity, "1");
            productFound = null;
            GUICommons.enabledButton(btnComplete);
            GUICommons.enabledButton(btnDebtors);
        } catch (BloSalesV2Exception ex) {
            logger.error(ex.getMessage());
            CommonAlerts.openError(ex.getMessage());
        }
    }
    /**
     * filtra los productos por codigo de barras o nombre
     * @param term
     * @param isBarCode 
     */
    private void filterProduct(String term, boolean isBarCode) {
        productFound = products.stream().filter(v -> {
            if (isBarCode) {
                return v.getBarCode().equals(term);
            }
            return v.toString().contains(term);
        }).findAny().orElse(null);
    }
    
    /** AJUSTAR PARA USAR TABLA */
    private List<PojoIntSaleProductData> getProductData() {
        final var products = new ArrayList<PojoSaleProductData>();
        PojoSaleProductData productInfo;
        /** parsea los datos de una fila y crea un nuevo pojo para guardar */
        for (var i = 0; i < tblProductsSales.getRowCount(); i++) {
            // 1. Obtenemos el valor y lo limpiamos de espacios
            final var rawValue = tblProductsSales.getValueAt(i, 3).toString().trim();

            // 2. Reemplazamos la coma por punto (por si acaso el sistema usa formato latino)
            // y quitamos cualquier caracter que no sea número o punto
            final var cleanValue = rawValue.replace(",", ".");
            final var price = new BigDecimal(cleanValue);
            
            final var quantityBuy = tblProductsSales.getValueAt(i, 4).toString().trim();
            productInfo = new PojoSaleProductData();
            productInfo.setIdProduct((long) tblProductsSales.getValueAt(i, 0));
            productInfo.setQuantityOnSale(new BigDecimal(tblProductsSales.getValueAt(i, 2).toString()));
            productInfo.setPrice(price);
            productInfo.setProductBuyTotal(new BigDecimal(quantityBuy));
            products.add(productInfo);
        }
        final var productsInner = new ArrayList<PojoIntSaleProductData>();
        products.forEach(p -> productsInner.add(saleProductMapper.toInner(p)));
        return productsInner;
    }
    
    /**
     * recupera los datos de los productos de una base de datos
     * @throws BloSalesV2Exception 
     */
    private void retrieveProducts() throws BloSalesV2Exception {
        products = mapperProducts.toOuter(productsController.getAllProducts()).getProducts();
    }
    
    private void resetFields() {
        GUICommons.setTextToField(nmbQuantity, "1");
        final var model = (DefaultTableModel) tblProductsSales.getModel();
        model.setRowCount(0);
        tblProductsSales.repaint();
        GUICommons.setTextToField(lblTotal, "0");
    }
    
    private void disableButtons() {
        GUICommons.disabledButton(btnComplete);
        GUICommons.disabledButton(btnDebtors);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComplete;
    private javax.swing.JButton btnDebtors;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBarCode;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField nmbQuantity;
    private javax.swing.JPanel pnlPay;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JTable tblProductsSales;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
