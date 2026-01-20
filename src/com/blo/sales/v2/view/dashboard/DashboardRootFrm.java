package com.blo.sales.v2.view.dashboard;

import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.dashboard.panels.AllProducts;
import com.blo.sales.v2.view.dashboard.panels.Categories;
import com.blo.sales.v2.view.dashboard.panels.Console;
import com.blo.sales.v2.view.dashboard.panels.Debtors;
import com.blo.sales.v2.view.dashboard.panels.RegisterProduct;
import com.blo.sales.v2.view.dashboard.panels.Sales;
import com.blo.sales.v2.view.pojos.PojoLoggedInUser;

public class DashboardRootFrm extends javax.swing.JFrame {
    
    private PojoLoggedInUser userData;
    
    public DashboardRootFrm(PojoLoggedInUser userData) {
        this.userData = userData;
        initComponents();
        GUICommons.showPanel(content, new Sales(userData));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        mnuBar = new javax.swing.JMenuBar();
        itmSales = new javax.swing.JMenu();
        optAddSale = new javax.swing.JMenuItem();
        itmStock = new javax.swing.JMenu();
        optStock = new javax.swing.JMenuItem();
        optRegister = new javax.swing.JMenuItem();
        optCategory = new javax.swing.JMenuItem();
        itmAdmon = new javax.swing.JMenu();
        optDebtors = new javax.swing.JMenuItem();
        itmProg = new javax.swing.JMenu();
        optConsole = new javax.swing.JMenuItem();

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

        itmSales.setText("Ventas");

        optAddSale.setText("Registrar venta");
        optAddSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAddSaleActionPerformed(evt);
            }
        });
        itmSales.add(optAddSale);

        mnuBar.add(itmSales);

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

        itmAdmon.setText("Administracion");

        optDebtors.setText("Deudores");
        optDebtors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optDebtorsActionPerformed(evt);
            }
        });
        itmAdmon.add(optDebtors);

        mnuBar.add(itmAdmon);

        itmProg.setText("Programacion");

        optConsole.setText("Consola");
        optConsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optConsoleActionPerformed(evt);
            }
        });
        itmProg.add(optConsole);

        mnuBar.add(itmProg);

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

    private void optAddSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAddSaleActionPerformed
        GUICommons.showPanel(content, new Sales(userData));
    }//GEN-LAST:event_optAddSaleActionPerformed

    private void optConsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optConsoleActionPerformed
        GUICommons.showPanel(content, new Console());
    }//GEN-LAST:event_optConsoleActionPerformed

    private void optDebtorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optDebtorsActionPerformed
        GUICommons.showPanel(content, new Debtors(userData));
    }//GEN-LAST:event_optDebtorsActionPerformed
    
    private void openAllProducts() {
        GUICommons.showPanel(content, new AllProducts(userData));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    private javax.swing.JMenu itmAdmon;
    private javax.swing.JMenu itmProg;
    private javax.swing.JMenu itmSales;
    private javax.swing.JMenu itmStock;
    private javax.swing.JMenuBar mnuBar;
    private javax.swing.JMenuItem optAddSale;
    private javax.swing.JMenuItem optCategory;
    private javax.swing.JMenuItem optConsole;
    private javax.swing.JMenuItem optDebtors;
    private javax.swing.JMenuItem optRegister;
    private javax.swing.JMenuItem optStock;
    // End of variables declaration//GEN-END:variables
}
