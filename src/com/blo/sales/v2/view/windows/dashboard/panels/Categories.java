package com.blo.sales.v2.view.windows.dashboard.panels;

import com.blo.sales.v2.controller.ICategoriesController;
import com.blo.sales.v2.controller.impl.CategoriesControllerImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.windows.mappers.CategoryMapper;
import com.blo.sales.v2.view.windows.pojos.PojoCategory;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Categories extends javax.swing.JPanel {
    
    private CategoryMapper categoryMapper;
    
    private ICategoriesController categoriesController;

    public Categories() {
        categoryMapper = new CategoryMapper();
        categoriesController = new CategoriesControllerImpl();
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstCategories = new javax.swing.JList<>();
        lblCategoryName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        lblDescription = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();

        jScrollPane1.setViewportView(lstCategories);

        lblCategoryName.setText("Categoria");

        lblDescription.setText("Descripcion");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblDescription)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDescription))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblCategoryName)
                            .addGap(18, 18, 18)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSave))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(317, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 329, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCategoryName)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDescription)
                            .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnSave)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            final var categoryName = BloSalesV2Utils.getTextFromJText(txtName);
            final var categoryDescription = BloSalesV2Utils.getTextFromJText(txtDescription);
            final var newCategory = new PojoCategory();
            newCategory.setCategory(categoryName);
            newCategory.setDescription(categoryDescription);
            final var data = categoriesController.registerCategory(categoryMapper.toInner(newCategory));
            final var out = categoryMapper.toOuter(data);
            System.out.println(out.getIdCategory());
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCategoryName;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JList<String> lstCategories;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
