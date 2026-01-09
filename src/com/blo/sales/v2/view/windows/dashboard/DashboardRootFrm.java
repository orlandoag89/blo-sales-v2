package com.blo.sales.v2.view.windows.dashboard;

import com.blo.sales.v2.view.windows.dashboard.panels.Categories;
import com.blo.sales.v2.view.windows.dashboard.panels.RegisterProduct;
import com.blo.sales.v2.view.windows.pojos.PojoLoggedInUser;
import java.awt.BorderLayout;
import javax.swing.JPanel;
public class DashboardRootFrm extends javax.swing.JFrame {
    
    private PojoLoggedInUser userData;

    public DashboardRootFrm(PojoLoggedInUser userData) {
        this.userData = userData;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        mnuBar = new javax.swing.JMenuBar();
        itmStock = new javax.swing.JMenu();
        optCategory = new javax.swing.JMenuItem();
        optRegister = new javax.swing.JMenuItem();

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

        optCategory.setText("Categorias");
        optCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optCategoryActionPerformed(evt);
            }
        });
        itmStock.add(optCategory);

        optRegister.setText("Registrar");
        optRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optRegisterActionPerformed(evt);
            }
        });
        itmStock.add(optRegister);

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
        final var addProduct = new RegisterProduct();
        showPanel(addProduct);
    }//GEN-LAST:event_optRegisterActionPerformed

    private void optCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optCategoryActionPerformed
        final var categories = new Categories();
        showPanel(categories);
    }//GEN-LAST:event_optCategoryActionPerformed

    private void showPanel(JPanel p) {
        p.setSize(1000, 600); // Ajusta al tamaño de tu contenedor
        p.setLocation(0, 0);
        content.removeAll(); // Limpia el panel principal
        content.add(p, BorderLayout.CENTER); // Agrega el nuevo formulario
        content.revalidate(); // Refresca el diseño
        content.repaint();    // Redibuja la interfaz
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel content;
    private javax.swing.JMenu itmStock;
    private javax.swing.JMenuBar mnuBar;
    private javax.swing.JMenuItem optCategory;
    private javax.swing.JMenuItem optRegister;
    // End of variables declaration//GEN-END:variables
}
