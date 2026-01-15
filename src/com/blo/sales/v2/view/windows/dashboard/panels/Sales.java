package com.blo.sales.v2.view.windows.dashboard.panels;

import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.ISalesController;
import com.blo.sales.v2.controller.impl.ProductsControllerImpl;
import com.blo.sales.v2.controller.impl.SalesControllerImpl;
import com.blo.sales.v2.controller.pojos.PojoIntSaleProductData;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.windows.commons.GUICommons;
import com.blo.sales.v2.view.windows.mappers.PojoSaleMapper;
import com.blo.sales.v2.view.windows.mappers.PojoSaleProductDataMapper;
import com.blo.sales.v2.view.windows.mappers.WrapperPojoProductsMapper;
import com.blo.sales.v2.view.windows.pojos.PojoLoggedInUser;
import com.blo.sales.v2.view.windows.pojos.PojoProduct;
import com.blo.sales.v2.view.windows.pojos.PojoSaleProductData;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

public class Sales extends javax.swing.JPanel {
    
    private IProductsController productsController;
    
    private WrapperPojoProductsMapper mapperProducts;
    
    private List<PojoProduct> products;

    private DefaultListModel modelLst;
    
    private BigDecimal totalSale;
    
    private PojoProduct productFound;
    
    private ISalesController salesController;
    
    private PojoSaleProductDataMapper saleProductMapper;
    
    private PojoLoggedInUser userData;
    
    private PojoSaleMapper saleMapper;
    
    /**
     * Creates new form Sales
     */
    public Sales(PojoLoggedInUser userData) {
        productsController = ProductsControllerImpl.getInstance();
        mapperProducts = WrapperPojoProductsMapper.getInstance();
        modelLst = new DefaultListModel<String>();
        salesController = SalesControllerImpl.getInstance();
        saleProductMapper = PojoSaleProductDataMapper.getInstance();
        saleMapper = PojoSaleMapper.getInstance();
        this.userData = userData;
        totalSale = BigDecimal.ZERO;
        initComponents();
        resetFields();
        try {
            retrieveProducts();
            GUICommons.addDoubleClickOnListEvt(lstProductsSales, item -> {
                final var indexSelected = lstProductsSales.getSelectedIndex();
                if (indexSelected != -1) {
                    modelLst.remove(indexSelected);
                    final var price = item.substring(item.lastIndexOf("$") + 1, item.lastIndexOf("[")).trim();
                    System.out.println(price);
                    totalSale = totalSale.subtract(new BigDecimal(price));
                    GUICommons.setTextToLabel(lblTotal, "Total: $" + totalSale);
                    if (totalSale.compareTo(BigDecimal.ZERO) == 0) {
                        pnlPay.setVisible(false);
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

        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstProductsSales = new javax.swing.JList<>();
        lblTotal = new javax.swing.JLabel();
        pnlPay = new javax.swing.JPanel();
        btnComplete = new javax.swing.JButton();
        lblProductName = new javax.swing.JLabel();
        nmbQuantity = new javax.swing.JTextField();
        btnContinue = new javax.swing.JButton();
        lblQuantity = new javax.swing.JLabel();
        lblBarCode = new javax.swing.JLabel();

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jScrollPane1.setViewportView(lstProductsSales);

        btnComplete.setText("Completo");
        btnComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPayLayout = new javax.swing.GroupLayout(pnlPay);
        pnlPay.setLayout(pnlPayLayout);
        pnlPayLayout.setHorizontalGroup(
            pnlPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnComplete)
                .addContainerGap(275, Short.MAX_VALUE))
        );
        pnlPayLayout.setVerticalGroup(
            pnlPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnComplete)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        btnContinue.setText("Continuar");
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });

        lblQuantity.setText("Cantidad");

        lblBarCode.setText("Código de barras");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnContinue))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nmbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblQuantity)
                                    .addGap(56, 56, 56)
                                    .addComponent(lblBarCode))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pnlPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantity)
                    .addComponent(lblBarCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nmbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnContinue)
                    .addComponent(lblProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (evt.getKeyCode() == 10) {
            pnlPay.setVisible(true);
            try {
                final var termToSearch = GUICommons.getTextFromJText(txtSearch);
                /** busqueda por codigo de barras */
                if (BloSalesV2Utils.validateTextWithPattern(BloSalesV2Utils.ONLY_NUMBERS, termToSearch)) {
                    productFound = 
                            products.stream().filter(c -> c.getBarCode().equals(termToSearch)).findFirst().orElse(null);
                    GUICommons.setTextToLabel(lblProductName, productFound.getProduct());
                    //modelLst.addElement(productFound.toString());
                    //lstProductsSales.setModel(modelLst);
                } else {
                    productFound =
                            products.stream().filter(c -> c.getProduct().contains(termToSearch)).findFirst().orElse(null);
                    GUICommons.setTextToLabel(lblProductName, productFound.getProduct());
                }
                /*if (productFound != null) {
                    totalSale = totalSale.add(productFound.getPrice());
                }
                GUICommons.setTextToField(txtSearch, BloSalesV2Utils.EMPTY_STRING);
                GUICommons.setTextToLabel(lblTotal, "Total: $" + totalSale);*/
            } catch (BloSalesV2Exception ex) {
                Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteActionPerformed
        final var model = (DefaultListModel<String>) lstProductsSales.getModel();
        final var products = new ArrayList<PojoSaleProductData>();
        PojoSaleProductData productInfo;
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
        
        try {
            salesController.registerSale(totalSale, productsInner, this.userData.getIdUser());
            resetFields();
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCompleteActionPerformed

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        try {
            final var quantity = GUICommons.getTextFromJText(nmbQuantity);
            modelLst.addElement(productFound.toString() + " [" + quantity + "]");
            lstProductsSales.setModel(modelLst);
            if (productFound != null) {
                totalSale = totalSale.add(productFound.getPrice().multiply(new BigDecimal(quantity)));
            }
            GUICommons.setTextToField(txtSearch, BloSalesV2Utils.EMPTY_STRING);
            GUICommons.setTextToLabel(lblTotal, "Total: $" + totalSale);
            productFound = null;
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnContinueActionPerformed

    private void retrieveProducts() throws BloSalesV2Exception {
        products = mapperProducts.toOuter(productsController.getAllProducts()).getProducts();
    }
    
    private void resetFields() {
        GUICommons.setTextToField(nmbQuantity, "1");
        GUICommons.setTextToLabel(lblProductName, BloSalesV2Utils.EMPTY_STRING);
        lstProductsSales.clearSelection();
        GUICommons.setTextToLabel(lblTotal, "0");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComplete;
    private javax.swing.JButton btnContinue;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBarCode;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JList<String> lstProductsSales;
    private javax.swing.JTextField nmbQuantity;
    private javax.swing.JPanel pnlPay;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
