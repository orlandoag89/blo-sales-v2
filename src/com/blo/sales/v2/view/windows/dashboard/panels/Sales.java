package com.blo.sales.v2.view.windows.dashboard.panels;

import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.impl.ProductsControllerImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.windows.commons.GUICommons;
import com.blo.sales.v2.view.windows.mappers.WrapperPojoProductsMapper;
import com.blo.sales.v2.view.windows.pojos.PojoProduct;
import java.math.BigDecimal;
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
    
    /**
     * Creates new form Sales
     */
    public Sales() {
        productsController = ProductsControllerImpl.getInstance();
        mapperProducts = WrapperPojoProductsMapper.getInstance();
        modelLst = new DefaultListModel<String>();
        totalSale = BigDecimal.ZERO;
        initComponents();
        try {
            retrieveProducts();
            GUICommons.addDoubleClickOnListEvt(lstProductsSales, item -> {
                final var indexSelected = lstProductsSales.getSelectedIndex();
                if (indexSelected != -1) {
                    modelLst.remove(indexSelected);
                    final var price = item.substring(item.lastIndexOf("$") + 1).trim();
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

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jScrollPane1.setViewportView(lstProductsSales);

        btnComplete.setText("Completo");

        javax.swing.GroupLayout pnlPayLayout = new javax.swing.GroupLayout(pnlPay);
        pnlPay.setLayout(pnlPayLayout);
        pnlPayLayout.setHorizontalGroup(
            pnlPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnComplete)
                .addContainerGap(382, Short.MAX_VALUE))
        );
        pnlPayLayout.setVerticalGroup(
            pnlPayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnComplete)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 225, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(pnlPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (evt.getKeyCode() == 10) {
            pnlPay.setVisible(true);
            try {
                final var termToSearch = GUICommons.getTextFromJText(txtSearch);
                /** busqueda por codigo de barras */
                PojoProduct productFound = null;
                if (BloSalesV2Utils.validateTextWithPattern(BloSalesV2Utils.ONLY_NUMBERS, termToSearch)) {
                    productFound = 
                            products.stream().filter(c -> c.getBarCode().equals(termToSearch)).findFirst().orElse(null);
                    modelLst.addElement(productFound.toString());
                    lstProductsSales.setModel(modelLst);
                }
                if (productFound != null) {
                    totalSale = totalSale.add(productFound.getPrice());
                }
                GUICommons.setTextToField(txtSearch, BloSalesV2Utils.EMPTY_STRING);
                GUICommons.setTextToLabel(lblTotal, "Total: $" + totalSale);
            } catch (BloSalesV2Exception ex) {
                Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void retrieveProducts() throws BloSalesV2Exception {
        products = mapperProducts.toOuter(productsController.getAllProducts()).getProducts();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComplete;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JList<String> lstProductsSales;
    private javax.swing.JPanel pnlPay;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
