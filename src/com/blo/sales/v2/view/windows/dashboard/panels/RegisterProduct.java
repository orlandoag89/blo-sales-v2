package com.blo.sales.v2.view.windows.dashboard.panels;

import com.blo.sales.v2.controller.ICategoriesController;
import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.impl.CategoriesControllerImpl;
import com.blo.sales.v2.controller.impl.ProductsControllerImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.windows.commons.GUICommons;
import com.blo.sales.v2.view.windows.mappers.ProductMapper;
import com.blo.sales.v2.view.windows.mappers.WrapperPojoCategoriesMapper;
import com.blo.sales.v2.view.windows.pojos.PojoProduct;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

public class RegisterProduct extends javax.swing.JPanel {
    
    private ICategoriesController categories;
    
    private ProductMapper productMapper;
    
    private IProductsController productsController;
    
    private WrapperPojoCategoriesMapper categoriesMapper;

    public RegisterProduct() {
        categories = CategoriesControllerImpl.getInstance();
        productsController = ProductsControllerImpl.getInstance();
        productMapper = ProductMapper.getInstance();
        categoriesMapper = WrapperPojoCategoriesMapper.getInstance();
        
        initComponents();
        
        try {
            loadCategories();
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
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

        lblBarCode.setText("Código de barras");

        lblProductName.setText("Nombre");

        lblQuantity.setText("Cantidad en existencia");

        lblPrice.setText("Precio");

        lblSaleCost.setText("Costo de venta");

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProductName)
                            .addComponent(txtBarCode)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblQuantity)
                        .addGap(18, 18, 18)
                        .addComponent(nmbQuantity))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPrice)
                        .addGap(18, 18, 18)
                        .addComponent(nmbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblSaleCost)
                        .addGap(18, 18, 18)
                        .addComponent(nmbSaleCost, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lstMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addComponent(chkbxItsKg))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave)))
                .addContainerGap())
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
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuantity)
                    .addComponent(nmbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrice)
                    .addComponent(nmbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSaleCost)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nmbSaleCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lstMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkbxItsKg))
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addContainerGap(159, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            final var productName = GUICommons.getTextFromJText(txtProductName);
            final var barCode = GUICommons.getTextFromJText(txtBarCode);
            final var quantity = GUICommons.getNumberFromJText(nmbQuantity);
            final var price = GUICommons.getNumberFromJText(nmbPrice);
            final var costOfSale = GUICommons.getNumberFromJText(nmbSaleCost);
            final var data = new PojoProduct();
            data.setBarCode(barCode);
            data.setCostOfSale(costOfSale);
            data.setPrice(price);
            data.setProduct(productName);
            data.setQuantity(quantity);
            /** selecciona una categoria */
            final var itemSelected = GUICommons.getValueFromComboBox(lstMarks).split("[ ]+");
            BloSalesV2Utils.validateRule(itemSelected.length == 0 || itemSelected[0].trim().isBlank(), "No se ha seleccionado un item");
            final var idMark = itemSelected[0].trim();
            data.setFkCategory(Long.parseLong(idMark));
            data.setKg(GUICommons.isCheckedCkeckBox(chkbxItsKg));
            productsController.registerProduct(productMapper.toInner(data));
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(RegisterProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void loadCategories() throws BloSalesV2Exception {
        final var categoryModel = new DefaultComboBoxModel<String>();
        
        categoriesMapper.toOuter(this.categories.getAllCategories())
                .getCategories().forEach(c -> categoryModel.addElement(c.toString()));
        
        lstMarks.setModel(categoryModel);
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
