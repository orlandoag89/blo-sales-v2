package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.ISalesController;
import com.blo.sales.v2.controller.impl.ProductsControllerImpl;
import com.blo.sales.v2.controller.impl.SalesControllerImpl;
import com.blo.sales.v2.controller.pojos.PojoIntSaleProductData;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.dialogs.SelectorDialog;
import com.blo.sales.v2.view.mappers.PojoSaleMapper;
import com.blo.sales.v2.view.mappers.PojoSaleProductDataMapper;
import com.blo.sales.v2.view.mappers.WrapperPojoProductsMapper;
import com.blo.sales.v2.view.pojos.PojoLoggedInUser;
import com.blo.sales.v2.view.pojos.PojoProduct;
import com.blo.sales.v2.view.pojos.PojoSaleProductData;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
    
    /**
     * Creates new form Sales
     */
    public Sales(PojoLoggedInUser userData) {
        productsController = ProductsControllerImpl.getInstance();
        mapperProducts = WrapperPojoProductsMapper.getInstance();
        modelLst = new DefaultListModel<String>();
        salesController = SalesControllerImpl.getInstance();
        saleProductMapper = PojoSaleProductDataMapper.getInstance();
        this.userData = userData;
        totalSale = BigDecimal.ZERO;
        initComponents();
        resetFields();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        lstProductsSales = new javax.swing.JList<>();
        lblTotal = new javax.swing.JLabel();
        pnlPay = new javax.swing.JPanel();
        btnComplete = new javax.swing.JButton();
        pnlSearch = new javax.swing.JPanel();
        lblQuantity = new javax.swing.JLabel();
        nmbQuantity = new javax.swing.JTextField();
        txtSearch = new javax.swing.JTextField();
        btnContinue = new javax.swing.JButton();
        lblBarCode = new javax.swing.JLabel();
        btnByName = new javax.swing.JButton();
        lblProductName = new javax.swing.JLabel();

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

        lblQuantity.setText("Cantidad");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        btnContinue.setText("Continuar");
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });

        lblBarCode.setText("Código de barras");

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchLayout.createSequentialGroup()
                        .addComponent(lblProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnContinue))
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nmbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQuantity))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSearchLayout.createSequentialGroup()
                                .addComponent(lblBarCode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnByName))
                            .addComponent(txtSearch))))
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
                .addGap(18, 18, 18)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContinue))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (evt.getKeyCode() == 10) {
            pnlPay.setVisible(true);
            try {
                final var termToSearch = GUICommons.getTextFromJText(txtSearch);
                /** busqueda por codigo de barras */
                if (BloSalesV2Utils.validateTextWithPattern(BloSalesV2Utils.ONLY_NUMBERS, termToSearch)) {
                    filterProduct(termToSearch, true);
                }
            } catch (BloSalesV2Exception ex) {
                Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteActionPerformed
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
            /** agrega un elemento a la lista de productos */
            modelLst.addElement(productFound.toString() + " [" + quantity + "]");
            lstProductsSales.setModel(modelLst);
            if (productFound != null) {
                totalSale = totalSale.add(productFound.getPrice().multiply(new BigDecimal(quantity)));
            }
            GUICommons.setTextToField(txtSearch, BloSalesV2Utils.EMPTY_STRING);
            GUICommons.setTextToLabel(lblTotal, "Total: $" + totalSale);
            GUICommons.setTextToField(nmbQuantity, "1");
            productFound = null;
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnContinueActionPerformed

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
            });
       dialog.setVisible(true);
    }//GEN-LAST:event_btnByNameActionPerformed

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
    
    /**
     * recupera los datos de los productos de una base de datos
     * @throws BloSalesV2Exception 
     */
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
    private javax.swing.JButton btnByName;
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
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
