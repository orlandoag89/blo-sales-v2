package com.blo.sales.v2.view.dashboard;

import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.dashboard.panels.AllCashboxes;
import com.blo.sales.v2.view.dashboard.panels.AllProducts;
import com.blo.sales.v2.view.dashboard.panels.CashboxOpen;
import com.blo.sales.v2.view.dashboard.panels.Categories;
import com.blo.sales.v2.view.dashboard.panels.Console;
import com.blo.sales.v2.view.dashboard.panels.Debtors;
import com.blo.sales.v2.view.dashboard.panels.Notes;
import com.blo.sales.v2.view.dashboard.panels.RegisterProduct;
import com.blo.sales.v2.view.dashboard.panels.Sales;
import com.blo.sales.v2.view.dashboard.panels.SalesReport;
import com.blo.sales.v2.view.dashboard.panels.SalesToday;
import com.blo.sales.v2.view.pojos.PojoLoggedInUser;

public class DashboardRootFrm extends javax.swing.JFrame {
    
    private PojoLoggedInUser userData;
    
    public DashboardRootFrm(PojoLoggedInUser userData) {
        this.userData = userData;
        initComponents();
        GUICommons.allWindow(this);
        GUICommons.showPanel(content, new Sales(userData));
        GUICommons.setTextToField(lblVersion, BloSalesV2Utils.VERSION);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        lblVersion = new javax.swing.JLabel();
        mnuBar = new javax.swing.JMenuBar();
        itmSales = new javax.swing.JMenu();
        optAddSale = new javax.swing.JMenuItem();
        optViewSales = new javax.swing.JMenuItem();
        itmStock = new javax.swing.JMenu();
        optRegister = new javax.swing.JMenuItem();
        optStock = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        optCategory = new javax.swing.JMenuItem();
        itmAdmon = new javax.swing.JMenu();
        itmContability = new javax.swing.JMenu();
        optOpoenCashbox = new javax.swing.JMenuItem();
        optAllCashboxes = new javax.swing.JMenuItem();
        sprt01 = new javax.swing.JPopupMenu.Separator();
        optSalesReport = new javax.swing.JMenuItem();
        optNotes = new javax.swing.JMenuItem();
        optDebtors = new javax.swing.JMenuItem();
        itmProg = new javax.swing.JMenu();
        optConsole = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 658, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 493, Short.MAX_VALUE)
        );

        itmSales.setText("Ventas");

        optAddSale.setText("Registrar venta");
        optAddSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAddSaleActionPerformed(evt);
            }
        });
        itmSales.add(optAddSale);

        optViewSales.setText("Ver");
        optViewSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optViewSalesActionPerformed(evt);
            }
        });
        itmSales.add(optViewSales);

        mnuBar.add(itmSales);

        itmStock.setText("Inventario");

        optRegister.setText("Registrar producto");
        optRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optRegisterActionPerformed(evt);
            }
        });
        itmStock.add(optRegister);

        optStock.setText("Ver");
        optStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optStockActionPerformed(evt);
            }
        });
        itmStock.add(optStock);
        itmStock.add(jSeparator1);

        optCategory.setText("Categorias");
        optCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optCategoryActionPerformed(evt);
            }
        });
        itmStock.add(optCategory);

        mnuBar.add(itmStock);

        itmAdmon.setText("Administracion");

        itmContability.setText("Contabilidad");

        optOpoenCashbox.setText("Caja");
        optOpoenCashbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optOpoenCashboxActionPerformed(evt);
            }
        });
        itmContability.add(optOpoenCashbox);

        optAllCashboxes.setText("Cajas");
        optAllCashboxes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAllCashboxesActionPerformed(evt);
            }
        });
        itmContability.add(optAllCashboxes);
        itmContability.add(sprt01);

        optSalesReport.setText("Ventas");
        optSalesReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optSalesReportActionPerformed(evt);
            }
        });
        itmContability.add(optSalesReport);

        itmAdmon.add(itmContability);

        optNotes.setText("Notas Rápidas");
        optNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optNotesActionPerformed(evt);
            }
        });
        itmAdmon.add(optNotes);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void optOpoenCashboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optOpoenCashboxActionPerformed
        GUICommons.showPanel(content, new CashboxOpen(userData));
    }//GEN-LAST:event_optOpoenCashboxActionPerformed

    private void optViewSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optViewSalesActionPerformed
        //GUICommons.showPanel(content, new SalesViewer());
        GUICommons.showPanel(content, new SalesToday());
    }//GEN-LAST:event_optViewSalesActionPerformed

    private void optAllCashboxesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAllCashboxesActionPerformed
        GUICommons.showPanel(content, new AllCashboxes());
    }//GEN-LAST:event_optAllCashboxesActionPerformed

    private void optNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optNotesActionPerformed
        GUICommons.showPanel(content, new Notes(userData));
    }//GEN-LAST:event_optNotesActionPerformed

    private void optSalesReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optSalesReportActionPerformed
        GUICommons.showPanel(content, new SalesReport());
    }//GEN-LAST:event_optSalesReportActionPerformed
    
    private void openAllProducts() {
        GUICommons.showPanel(content, new AllProducts(userData));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    private javax.swing.JMenu itmAdmon;
    private javax.swing.JMenu itmContability;
    private javax.swing.JMenu itmProg;
    private javax.swing.JMenu itmSales;
    private javax.swing.JMenu itmStock;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JMenuBar mnuBar;
    private javax.swing.JMenuItem optAddSale;
    private javax.swing.JMenuItem optAllCashboxes;
    private javax.swing.JMenuItem optCategory;
    private javax.swing.JMenuItem optConsole;
    private javax.swing.JMenuItem optDebtors;
    private javax.swing.JMenuItem optNotes;
    private javax.swing.JMenuItem optOpoenCashbox;
    private javax.swing.JMenuItem optRegister;
    private javax.swing.JMenuItem optSalesReport;
    private javax.swing.JMenuItem optStock;
    private javax.swing.JMenuItem optViewSales;
    private javax.swing.JPopupMenu.Separator sprt01;
    // End of variables declaration//GEN-END:variables
}
