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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;

public class Sales extends javax.swing.JPanel {
    
    private static final GUILogger logger = GUILogger.getLogger(Sales.class.getName());
    
    private IProductsController productsController = ProductsControllerImpl.getInstance();
    
    private WrapperPojoProductsMapper mapperProducts = WrapperPojoProductsMapper.getInstance();
    
    private ISalesController salesController = SalesControllerImpl.getInstance();
    
    private IDebtorsController debtorsController = DebtorsControllerImpl.getInstance();
    
    private PojoSaleProductDataMapper saleProductMapper = PojoSaleProductDataMapper.getInstance();
    
    private WrapperDebtorsMapper wrapperDebtorsMapper = WrapperDebtorsMapper.getInstance();
    
    private DebtorMapper debtorMapper = DebtorMapper.getInstance();
    
    private List<PojoProduct> products;

    private DefaultListModel modelLst;
    
    private BigDecimal totalSale;
    
    private PojoProduct productFound;
    
    private PojoLoggedInUser userData;
        
    public Sales(PojoLoggedInUser userData) {
        modelLst = new DefaultListModel<String>();
        this.userData = userData;
        totalSale = BigDecimal.ZERO;
        initComponents();
        resetFields();
        disableButtons();
        try {
            retrieveProducts();
            GUICommons.addDoubleClickOnListEvt(lstProductsSales, item -> {
                /** elimina un item de una lista */
                final var indexSelected = lstProductsSales.getSelectedIndex();
                if (indexSelected != -1) {
                    modelLst.remove(indexSelected);
                    final var price = item.substring(item.lastIndexOf("$") + 1, item.lastIndexOf("[")).trim();
                    totalSale = totalSale.subtract(new BigDecimal(price));
                    GUICommons.setTextToLabel(lblTotal, "Total: $" + totalSale);
                    if (totalSale.compareTo(BigDecimal.ZERO) == 0) {
                        disableButtons();
                    }
                }
            });
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtSearch.requestFocusInWindow();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstProductsSales = new javax.swing.JList<>();
        lblTotal = new javax.swing.JLabel();
        pnlPay = new javax.swing.JPanel();
        btnComplete = new javax.swing.JButton();
        btnDebtors = new javax.swing.JButton();
        pnlSearch = new javax.swing.JPanel();
        lblQuantity = new javax.swing.JLabel();
        nmbQuantity = new javax.swing.JTextField();
        txtSearch = new javax.swing.JTextField();
        lblBarCode = new javax.swing.JLabel();
        btnByName = new javax.swing.JButton();

        jScrollPane1.setViewportView(lstProductsSales);

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
                .addContainerGap()
                .addComponent(btnComplete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
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
                .addContainerGap(206, Short.MAX_VALUE))
        );

        lblQuantity.setText("Cantidad");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        lblBarCode.setText("Código de barras / nombre");

        btnByName.setText("Por nombre");
        btnByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnByNameActionPerformed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                        .addComponent(btnByName))
                    .addComponent(txtSearch))
                .addGap(6, 6, 6))
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnByName)
                    .addComponent(lblBarCode)
                    .addComponent(lblQuantity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nmbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pnlPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (evt.getKeyCode() == 10) {
            try {
                final var termToSearch = GUICommons.getTextFromJText(txtSearch);
                /** busqueda por codigo de barras */
                if (BloSalesV2Utils.validateTextWithPattern(BloSalesV2Utils.ONLY_NUMBERS, termToSearch)) {
                    filterProduct(termToSearch, true);
                    addItemToList();
                }
            } catch (BloSalesV2Exception ex) {
                Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteActionPerformed
        try {
            salesController.registerSale(totalSale, getProductData(), this.userData.getIdUser());
            disableButtons();
            GUICommons.setTextToLabel(lblTotal, "Total: 0");
            totalSale = BigDecimal.ZERO;
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCompleteActionPerformed

    private void btnByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnByNameActionPerformed
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
    }//GEN-LAST:event_btnByNameActionPerformed

    private void btnDebtorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDebtorsActionPerformed
        try {
            final var debtors = wrapperDebtorsMapper.toOuter(debtorsController.getAllDebtors());
            final var debtorsDialog = new DebtorsDialog<>(
                this,
                "Deudores",
                debtors.getDebtors(),
                totalSale,
                item -> {
                    try {
                        /** formato de pagos amountTIMESTAMPtimestamp */
                        logger.log("deudor " + item.toString());
                        final var pay = BloSalesV2Utils.getFirstLastPayment(item.getPayments(), BloSalesV2UtilsEnum.LAST);
                        /** es nuevo deudor  */
                        if (item.getIdDebtor() == 0) {
                            salesController.registerSaleWithNewDebtor(
                                pay,
                                getProductData(),
                                userData.getIdUser(),
                                debtorMapper.toInner(item)
                            );
                        } else {
                            /** validar pagos ventas */
                            salesController.registerSaleWithDebtor(
                                item.getDebt(),
                                getProductData(),
                                pay,
                                item.getPayments(),
                                userData.getIdUser(),
                                item.getIdDebtor());
                        }
                    } catch (BloSalesV2Exception ex) {
                        Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            debtorsDialog.setVisible(true);
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDebtorsActionPerformed

    private void addItemToList() {
        try {
            if (productFound == null) {
                throw new BloSalesV2Exception("Producto no seleccionado");
            }
            final var quantity = GUICommons.getTextFromJText(nmbQuantity);
            // valida si se puede con pesos solamente si el producto se vende por kg
            var onSaleQuantity = new BigDecimal(BigInteger.ZERO);
            if (quantity.startsWith("P") && productFound.isKg()) {
                // Extraemos el valor numérico después de la 'P'
                final var cash = new BigDecimal(quantity.substring(1));
                final var price = productFound.getPrice();
                // 1. Calculamos la fracción de kilo (10 / 154)
                // 2. Multiplicamos por 1000 para pasar a gramos
                // 3. Redondeamos al final a 2 decimales
                onSaleQuantity = cash.divide(price, 6, RoundingMode.HALF_UP)
                    //.multiply(new BigDecimal("1000"))
                    .setScale(4, RoundingMode.HALF_UP);
                totalSale = totalSale.add(cash);
            } else {
                // Si no empieza con P, se asume que la cantidad ya es numérica (gramos o piezas)
                onSaleQuantity = new BigDecimal(quantity);
                totalSale = totalSale.add(productFound.getPrice().multiply(onSaleQuantity));
                
            }
            modelLst.addElement(productFound.toString() + " [" + onSaleQuantity + "]");
            lstProductsSales.setModel(modelLst);
            GUICommons.setTextToField(txtSearch, BloSalesV2Utils.EMPTY_STRING);
            GUICommons.setTextToLabel(lblTotal, "Total: $" + totalSale);
            GUICommons.setTextToField(nmbQuantity, "1");
            productFound = null;
            GUICommons.enabledButton(btnComplete);
            GUICommons.enabledButton(btnDebtors);
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private List<PojoIntSaleProductData> getProductData() {
        final var model = (DefaultListModel<String>) lstProductsSales.getModel();
        final var products = new ArrayList<PojoSaleProductData>();
        PojoSaleProductData productInfo;
        /** parsea los datos de una fila y crea un nuevo pojo para guardar */
        for (var i = 0; i < model.size(); i++) {
            final var item = model.get(i);
            productInfo = new PojoSaleProductData();
            //idProduct product $price [quantity]
            // 1. Obtener el ID (desde el inicio hasta el primer espacio)
            final var primerEspacio = item.indexOf(" ");
            final var id = item.substring(0, primerEspacio);
            // 2. Obtener la cantidad (lo que está entre [ y ])
            final var quantity = item.substring(item.indexOf("[") + 1, item.indexOf("]"));
            // 3. Obtener el precio (lo que está entre $ y el espacio antes del [)
            final var price = item.substring(item.indexOf("$") + 1, item.indexOf("[")).trim();
            // 4. Obtener el nombre (lo que está entre el ID y el $)
            //final var name = item.substring(primerEspacio, item.indexOf("$")).trim();
            productInfo.setIdProduct(Long.parseLong(id));
            productInfo.setPrice(new BigDecimal(price));
            productInfo.setQuantityOnSale(new BigDecimal(quantity));
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
        lstProductsSales.clearSelection();
        GUICommons.setTextToLabel(lblTotal, "0");
    }
    
    private void disableButtons() {
        GUICommons.disabledButton(btnComplete);
        GUICommons.disabledButton(btnDebtors);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnByName;
    private javax.swing.JButton btnComplete;
    private javax.swing.JButton btnDebtors;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBarCode;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JList<String> lstProductsSales;
    private javax.swing.JTextField nmbQuantity;
    private javax.swing.JPanel pnlPay;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
