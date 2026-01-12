package com.blo.sales.v2.view.windows.dashboard;

import com.blo.sales.v2.view.windows.commons.GUICommons;
import com.blo.sales.v2.view.windows.dashboard.panels.AllProducts;
import com.blo.sales.v2.view.windows.dashboard.panels.Categories;
import com.blo.sales.v2.view.windows.dashboard.panels.RegisterProduct;
import com.blo.sales.v2.view.windows.pojos.PojoLoggedInUser;

public class DashboardRootFrm extends javax.swing.JFrame {
    
    private PojoLoggedInUser userData;
    
    public DashboardRootFrm(PojoLoggedInUser userData) {
        this.userData = userData;
        initComponents();
        openAllProducts();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        mnuBar = new javax.swing.JMenuBar();
        itmStock = new javax.swing.JMenu();
        optStock = new javax.swing.JMenuItem();
        optRegister = new javax.swing.JMenuItem();
        optCategory = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 667, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 504, Short.MAX_VALUE)
        );

        itmStock.setText("Inventario");

        optStock.setText("Ver");
        optStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optStockActionPerformed(evt);
            }
        });
        itmStock.add(optStock);

        optRegister.setText("Registrar producto");
        optRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optRegisterActionPerformed(evt);
            }
        });
        itmStock.add(optRegister);

        optCategory.setText("Categorias");
        optCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optCategoryActionPerformed(evt);
            }
        });
        itmStock.add(optCategory);

        mnuBar.add(itmStock);

        setJMenuBar(mnuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void optRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optRegisterActionPerformed
        GUICommons.showPanel(content, new RegisterProduct());
    }//GEN-LAST:event_optRegisterActionPerformed

    private void optCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optCategoryActionPerformed
        GUICommons.showPanel(content, new Categories());
    }//GEN-LAST:event_optCategoryActionPerformed

    private void optStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optStockActionPerformed
        openAllProducts();
    }//GEN-LAST:event_optStockActionPerformed
    
    private void openAllProducts() {
        GUICommons.showPanel(content, new AllProducts(userData));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    private javax.swing.JMenu itmStock;
    private javax.swing.JMenuBar mnuBar;
    private javax.swing.JMenuItem optCategory;
    private javax.swing.JMenuItem optRegister;
    private javax.swing.JMenuItem optStock;
    // End of variables declaration//GEN-END:variables
}
