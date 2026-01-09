package com.blo.sales.v2.view.windows.dashboard.panels;

public class RegisterProduct extends javax.swing.JPanel {

    public RegisterProduct() {
        initComponents();
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

        lstMarks.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                        .addGap(0, 173, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSave)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(chkbxItsKg)))
                .addGap(42, 42, 42))
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
                        .addComponent(lstMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(chkbxItsKg)
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addGap(38, 38, 38))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        
    }//GEN-LAST:event_btnSaveActionPerformed


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
