package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.ICategoriesController;
import com.blo.sales.v2.controller.impl.CategoriesControllerImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.alerts.CommonAlerts;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.mappers.CategoryMapper;
import com.blo.sales.v2.view.mappers.WrapperPojoCategoriesMapper;
import com.blo.sales.v2.view.pojos.PojoCategory;
import com.blo.sales.v2.view.pojos.WrapperPojoCategories;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

public class Categories extends javax.swing.JPanel {
    
    private static final CategoryMapper categoryMapper = CategoryMapper.getInstance();
    
    private static final WrapperPojoCategoriesMapper wrapperPojoCategoriesMapper = WrapperPojoCategoriesMapper.getInstance();
    
    private static final ICategoriesController categoriesController = CategoriesControllerImpl.getInstance();
    
    /** Variable global para almacenar categorias y usarla en cualquer metodo */
    private WrapperPojoCategories categoriesGlobal;

    public Categories() {
        initComponents();
        loadCategories();
        content.setVisible(false);
        lblIdCategory.setVisible(false);
        GUICommons.addDoubleClickOnListEvt(lstCategories, item -> {
            try {
                setData(item);
            } catch (BloSalesV2Exception ex) {
                Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
                CommonAlerts.openError(ex.getMessage());
            }
        });
    }
    
    private void setData(String item) throws BloSalesV2Exception {
        content.setVisible(true);
        final var idSep = item.split(" +");
        // valida que exista un id
        BloSalesV2Utils.validateRule(
                idSep.length == 0 || idSep[0].trim().isBlank(),
                BloSalesV2Utils.CODE_CATEGORY_NOT_SELECTED,
                BloSalesV2Utils.CATEGORY_NOT_SELECTED);
        final var id = idSep[0].trim();
        // filtro de categorias
        final var itemFound = 
                categoriesGlobal.getCategories().stream().filter(c -> c.getIdCategory() == Long.parseLong(id)).
                findFirst().orElse(null);
        if (itemFound != null) {
            GUICommons.setTextToField(txtEditName, itemFound.getCategory());
            GUICommons.setTextToField(txtEditDescription, itemFound.getDescription());
            GUICommons.setTextToField(lblIdCategory, id);
        }
        
        
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
        content = new javax.swing.JPanel();
        txtEditName = new javax.swing.JTextField();
        txtEditDescription = new javax.swing.JTextField();
        btnSaveChanges = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblEditCategory = new javax.swing.JLabel();
        lblIdCategory = new javax.swing.JLabel();

        jScrollPane1.setViewportView(lstCategories);

        lblCategoryName.setText("Categoria");

        lblDescription.setText("Descripcion");

        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnSaveChanges.setText("Guardar cambios");
        btnSaveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveChangesActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblEditCategory.setText("Editar una categoria");

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addGap(18, 18, 18)
                        .addComponent(btnSaveChanges))
                    .addComponent(txtEditDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEditName, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentLayout.createSequentialGroup()
                        .addComponent(lblEditCategory)
                        .addGap(18, 18, 18)
                        .addComponent(lblIdCategory)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEditCategory)
                    .addComponent(lblIdCategory))
                .addGap(18, 18, 18)
                .addComponent(txtEditName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtEditDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveChanges)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCategoryName)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDescription)
                            .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave)))
                .addGap(18, 18, 18)
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /** guarda una nueva categoria */
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            final var categoryName = GUICommons.getTextFromField(txtName, true);
            final var categoryDescription = GUICommons.getTextFromField(txtDescription, true);
            final var newCategory = new PojoCategory();
            newCategory.setCategory(categoryName);
            newCategory.setDescription(categoryDescription);
            categoriesController.registerCategory(categoryMapper.toInner(newCategory));
            /** recarga la lista de categorias */
            loadCategories();
            /** limpiar campos */
            GUICommons.setTextToField(txtName, BloSalesV2Utils.EMPTY_STRING);
            GUICommons.setTextToField(txtDescription, BloSalesV2Utils.EMPTY_STRING);
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
            CommonAlerts.openError(ex.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        content.setVisible(false);
        GUICommons.setTextToField(lblIdCategory, BloSalesV2Utils.EMPTY_STRING);
        GUICommons.setTextToField(txtEditDescription, BloSalesV2Utils.EMPTY_STRING);
        GUICommons.setTextToField(txtEditName, BloSalesV2Utils.EMPTY_STRING);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveChangesActionPerformed
        try {
            final var newName = GUICommons.getTextFromField(txtEditName, true);
            final var newDescription = GUICommons.getTextFromField(txtEditDescription, true);
            final var data = new PojoCategory();
            data.setCategory(newName);
            data.setDescription(newDescription);
            categoriesController.updateCategory(Long.parseLong(GUICommons.getTextFromField(lblIdCategory, true)), categoryMapper.toInner(data));
            /** actualizar la lista de categorias */
            loadCategories();
            btnCancelActionPerformed(evt);
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
            CommonAlerts.openError(ex.getMessage());
        }
    }//GEN-LAST:event_btnSaveChangesActionPerformed

    /** recupera todas las categorias y las envia a la lista */
    private void loadCategories() {
        try {
            final var categoriesInn = categoriesController.getAllCategories();
            categoriesGlobal = wrapperPojoCategoriesMapper.toOuter(categoriesInn);
            final var model = new DefaultListModel<String>();
            if (categoriesGlobal.getCategories() != null && !categoriesGlobal.getCategories().isEmpty()) {
                categoriesGlobal.getCategories().forEach(c -> {
                    model.addElement(c.toString());
                });
            }
            lstCategories.setModel(model);
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Categories.class.getName()).log(Level.SEVERE, null, ex);
            CommonAlerts.openError(ex.getMessage());
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveChanges;
    private javax.swing.JPanel content;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCategoryName;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblEditCategory;
    private javax.swing.JLabel lblIdCategory;
    private javax.swing.JList<String> lstCategories;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtEditDescription;
    private javax.swing.JTextField txtEditName;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
