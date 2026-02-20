package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.ICategoriesController;
import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.impl.CategoriesControllerImpl;
import com.blo.sales.v2.controller.impl.ProductsControllerImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.CommonAlerts;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.commons.GUILogger;
import com.blo.sales.v2.view.mappers.ProductMapper;
import com.blo.sales.v2.view.mappers.WrapperPojoCategoriesMapper;
import com.blo.sales.v2.view.pojos.PojoProduct;
import com.blo.sales.v2.view.utils.GUIStore;
import com.blo.sales.v2.view.utils.handler.ManagementProductStoreHandler;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class RegisterProduct extends javax.swing.JPanel {
    
    private static final GUILogger logger = GUILogger.getLogger(RegisterProduct.class.getName());
    
    private static final ICategoriesController categories = CategoriesControllerImpl.getInstance();
    
    private static final ProductMapper productMapper = ProductMapper.getInstance();
    
    private static final IProductsController productsController = ProductsControllerImpl.getInstance();
    
    private static final WrapperPojoCategoriesMapper categoriesMapper = WrapperPojoCategoriesMapper.getInstance();

    public RegisterProduct() {
        initComponents();
        loadDataForm();
        try {
            loadCategories();
        } catch (BloSalesV2Exception ex) {
            logger.error(ex.getMessage());
            CommonAlerts.openError(ex.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBarCode = new javax.swing.JTextField();
        lblBarCode = new javax.swing.JLabel();
        lblProductName = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        lblQuantity = new javax.swing.JLabel();
        nmbQuantity = new javax.swing.JTextField();
        lblPrice = new javax.swing.JLabel();
        nmbPrice = new javax.swing.JTextField();
        lblSaleCost = new javax.swing.JLabel();
        nmbSaleCost = new javax.swing.JTextField();
        chkbxItsKg = new javax.swing.JCheckBox();
        btnSave = new javax.swing.JButton();
        lstMarks = new javax.swing.JComboBox<>();

        txtBarCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBarCodeKeyReleased(evt);
            }
        });

        lblBarCode.setText("Código de barras");

        lblProductName.setText("Nombre");

        txtProductName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductNameKeyReleased(evt);
            }
        });

        lblQuantity.setText("Cantidad en existencia");

        nmbQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nmbQuantityKeyReleased(evt);
            }
        });

        lblPrice.setText("Precio");

        nmbPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nmbPriceKeyReleased(evt);
            }
        });

        lblSaleCost.setText("Costo de venta");

        nmbSaleCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nmbSaleCostKeyReleased(evt);
            }
        });

        chkbxItsKg.setText("¿Por kg?");

        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblProductName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBarCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(47, 47, 47)
                        .addComponent(txtBarCode)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblQuantity)
                                .addGap(18, 18, 18)
                                .addComponent(nmbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPrice)
                                .addGap(18, 18, 18)
                                .addComponent(nmbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblSaleCost)
                                .addGap(18, 18, 18)
                                .addComponent(nmbSaleCost, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(lstMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addComponent(btnSave)
                                .addGap(15, 15, 15))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(chkbxItsKg)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBarCode))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProductName)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkbxItsKg)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuantity)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nmbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPrice)
                        .addComponent(nmbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSaleCost)
                        .addComponent(nmbSaleCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lstMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSave)))
                .addContainerGap(239, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            final var productName = GUICommons.getTextFromField(txtProductName, true);
            final var barCode = GUICommons.getTextFromField(txtBarCode, true);
            final var quantity = GUICommons.getNumberFromJText(nmbQuantity, 3);
            final var price = GUICommons.getNumberFromJText(nmbPrice, 2);
            final var costOfSale = GUICommons.getNumberFromJText(nmbSaleCost, 2);
            final var data = new PojoProduct();
            data.setBarCode(barCode);
            data.setCostOfSale(costOfSale);
            data.setPrice(price);
            data.setProduct(productName);
            data.setQuantity(quantity);
            /** selecciona una categoria */
            final var itemSelected = GUICommons.getValueFromComboBox(lstMarks).split("[ ]+");
            BloSalesV2Utils.validateRule(
                    itemSelected.length == 0 || itemSelected[0].trim().isBlank(),
                    BloSalesV2Utils.CODE_CATEGORY_NOT_SELECTED,
                    BloSalesV2Utils.CATEGORY_NO_SELECTED
            );
            final var idMark = itemSelected[0].trim();
            data.setFkCategory(Long.parseLong(idMark));
            data.setKg(GUICommons.isCheckedCkeckBox(chkbxItsKg));
            GUIStore.resetProductData();
            productsController.registerProduct(productMapper.toInner(data));
            GUICommons.setTextToField(txtProductName, BloSalesV2Utils.EMPTY_STRING);
            GUICommons.setTextToField(txtBarCode, BloSalesV2Utils.EMPTY_STRING);
            GUICommons.setTextToField(nmbQuantity, BloSalesV2Utils.EMPTY_STRING);
            GUICommons.setTextToField(nmbPrice, BloSalesV2Utils.EMPTY_STRING);
            GUICommons.setTextToField(nmbSaleCost, BloSalesV2Utils.EMPTY_STRING);
        } catch (BloSalesV2Exception | NumberFormatException ex) {
            logger.error(ex.getMessage());
            CommonAlerts.openError(ex.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtBarCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarCodeKeyReleased
        addPojoData(txtBarCode, ManagementProductStoreHandler.BAR_CODE);
    }//GEN-LAST:event_txtBarCodeKeyReleased

    private void txtProductNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductNameKeyReleased
        addPojoData(txtProductName, ManagementProductStoreHandler.PRODUCT);
    }//GEN-LAST:event_txtProductNameKeyReleased

    private void nmbQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmbQuantityKeyReleased
        addPojoData(nmbQuantity, ManagementProductStoreHandler.QUANTITY);
    }//GEN-LAST:event_nmbQuantityKeyReleased

    private void nmbPriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmbPriceKeyReleased
        addPojoData(nmbPrice, ManagementProductStoreHandler.PRICE);
    }//GEN-LAST:event_nmbPriceKeyReleased

    private void nmbSaleCostKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmbSaleCostKeyReleased
        addPojoData(nmbSaleCost, ManagementProductStoreHandler.COST_OF_SALE);
    }//GEN-LAST:event_nmbSaleCostKeyReleased

    private void loadCategories() throws BloSalesV2Exception {
        final var categoryModel = new DefaultComboBoxModel<String>();
        
        categoriesMapper.toOuter(this.categories.getAllCategories())
                .getCategories().forEach(c -> categoryModel.addElement(c.toString()));
        
        lstMarks.setModel(categoryModel);
    }
    
    private void loadDataForm() {
        final var productStore = GUIStore.getProductData();
        setIfNotNull(productStore.getBarCode(), txtBarCode);
        setIfNotNull(productStore.getProduct(), txtProductName);
        setIfNotNull(productStore.getQuantity(), nmbQuantity);
        setIfNotNull(productStore.getPrice(), nmbPrice);
        setIfNotNull(productStore.getCostOfSale(), nmbSaleCost);
    }
    
    private void setIfNotNull(Object value, JTextField component) {
        if (value != null) {
            GUICommons.setTextToField(component, value.toString());
        }
    }
    
    private void addPojoData(JTextField field, ManagementProductStoreHandler prop) {
        try {
            final var value = GUICommons.getTextFromField(field, false);
            GUIStore.addPropOnPojoProduct(prop, value);
        } catch (BloSalesV2Exception ex) {
            logger.error(ex.getMessage());
            CommonAlerts.openError(ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkbxItsKg;
    private javax.swing.JLabel lblBarCode;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblSaleCost;
    private javax.swing.JComboBox<String> lstMarks;
    private javax.swing.JTextField nmbPrice;
    private javax.swing.JTextField nmbQuantity;
    private javax.swing.JTextField nmbSaleCost;
    private javax.swing.JTextField txtBarCode;
    private javax.swing.JTextField txtProductName;
    // End of variables declaration//GEN-END:variables
}
