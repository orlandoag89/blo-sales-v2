package com.blo.sales.v2.view.dialogs;

import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.commons.GUILogger;
import com.blo.sales.v2.view.pojos.PojoDebtor;
import java.awt.Component;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class DebtorsDialog<T> extends javax.swing.JDialog {
    
    private List<T> items;
    
    private BigDecimal totalSale;
    
    private String debtorName;
    
    private Consumer<T> callback;
    
    private PojoDebtor debtor;
    
    private static final GUILogger logger = GUILogger.getLogger(DebtorsDialog.class.getName());
    
    public DebtorsDialog(
            Component parent,
            String title,
            List<T> items,
            BigDecimal totalSale,
            Consumer<T> callback
    ) {
        super(SwingUtilities.getWindowAncestor(parent), title, ModalityType.APPLICATION_MODAL);
        this.items = items;
        this.totalSale = totalSale;
        this.callback = callback;
        initComponents();
        loadTitlesAndData();
        GUICommons.setTextToLabel(lblAmount, "$" + this.totalSale);
        GUICommons.disabledButton(btnRegister);
        GUICommons.disabledButton(btnSaveRegister);
        GUICommons.setTextToField(txtPartialPay, BigDecimal.ZERO + "");
        GUICommons.addDoubleClickOnTable(tblDebtors, item -> {
            debtor = 
                    items.stream().
                        map(i -> (PojoDebtor) i).
                        filter(i -> i.getIdDebtor() == Long.parseLong(item + "")).
                        findFirst().
                        orElse(null);
            if (debtor != null) {
                GUICommons.setTextToLabel(lblAmount, "$" + debtor.getDebt().add(totalSale));
                GUICommons.setTextToLabel(lblPartialPay, debtor.getName() + " deja ");
                GUICommons.enabledButton(btnSaveRegister);
                this.totalSale = this.totalSale.add(debtor.getDebt());
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tblContainer = new javax.swing.JScrollPane();
        tblDebtors = new javax.swing.JTable();
        txtName = new javax.swing.JTextField();
        lblAmount = new javax.swing.JLabel();
        pnlPayData = new javax.swing.JPanel();
        lblPartialPay = new javax.swing.JLabel();
        txtPartialPay = new javax.swing.JTextField();
        btnSaveRegister = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblDebtors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblContainer.setViewportView(tblDebtors);

        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNameKeyReleased(evt);
            }
        });

        lblPartialPay.setText("Dejó");

        txtPartialPay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPartialPayKeyReleased(evt);
            }
        });

        btnSaveRegister.setText("Guardar");
        btnSaveRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPayDataLayout = new javax.swing.GroupLayout(pnlPayData);
        pnlPayData.setLayout(pnlPayDataLayout);
        pnlPayDataLayout.setHorizontalGroup(
            pnlPayDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPayDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPayDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPartialPay)
                    .addComponent(txtPartialPay, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(btnSaveRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        pnlPayDataLayout.setVerticalGroup(
            pnlPayDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPayDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPartialPay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPartialPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSaveRegister)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnRegister.setText("Registrar");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tblContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlPayData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegister))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPayData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tblContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(170, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        try {
            debtorName = GUICommons.getTextFromJText(this.txtName);
            GUICommons.enabledButton(btnRegister);
            debtor = new PojoDebtor();
            debtor.setName(debtorName);
            debtor.setPayments(BloSalesV2Utils.EMPTY_STRING);
            debtor.setDebt(BigDecimal.ZERO);
            GUICommons.enabledButton(btnSaveRegister);
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(DebtorsDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void txtNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyReleased
        final var name = this.txtName.getText();
        if (evt.getKeyCode() == 8 && name != null && name.trim().isBlank()) {
            GUICommons.disabledButton(btnRegister);
            return;
        }
        GUICommons.enabledButton(btnRegister);
    }//GEN-LAST:event_txtNameKeyReleased

    private void btnSaveRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveRegisterActionPerformed
        try {
            final var partialPayment = GUICommons.getNumberFromJText(txtPartialPay);
            final var newPay = BloSalesV2Utils.getPartialPayment(partialPayment);
            // deudor existente
            if (debtor.getIdDebtor() != 0) {
                logger.log("deudor existente");
                var payments = debtor.getPayments();
                final var newDebt = new BigDecimal(GUICommons.getTextFromLabel(lblAmount).substring(1));
                logger.log("se realiza la resta del total acumulado menos el pago " + newDebt);
                // no se realizo pago
                debtor.setDebt(newDebt);
                // se realiza pago
                if (partialPayment.compareTo(BigDecimal.ZERO) != 0) {
                    logger.log("hay un pago extra, se arma cadena de pagos");
                    payments = payments + newPay;
                    debtor.setPayments(payments);
                    logger.log("pagos " + debtor.getPayments());
                }
                // se paga toda la duda
                if (newDebt.compareTo(BigDecimal.ZERO) <= 0) {
                    logger.log("se hizo el pago completo: " + totalSale);
                    debtor.setDebt(totalSale);
                }
                logger.log("nuevos datos del deudor " + debtor.toString());
                callback.accept((T) debtor);
                this.dispose();
                return;
            }
            logger.log("flujo de nuevo deudor");
            /** nuevo deudor flujo */
            if (partialPayment.compareTo(BigDecimal.ZERO) != 0) {
                debtor.setPayments(newPay);
            }
            debtor.setDebt(new BigDecimal(GUICommons.getTextFromLabel(lblAmount).substring(1)));
            logger.log("nuevo deudor" + debtor.toString());
            callback.accept((T) debtor);
            this.dispose();
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(DebtorsDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnSaveRegisterActionPerformed

    private void txtPartialPayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPartialPayKeyReleased
        final var partialPayTmp = txtPartialPay.getText().trim();
        if (
                evt.getKeyCode() == GUICommons.REMVOE_KEY_CODE ||
                evt.getKeyCode() == GUICommons.SUPR_KEY &&
                partialPayTmp.isBlank()
            ) {
            GUICommons.setTextToLabel(lblAmount, "$" + totalSale);
        }
        
        if (!partialPayTmp.isBlank() && BloSalesV2Utils.validateTextWithPattern(BloSalesV2Utils.CURRENCY_REGEX, partialPayTmp)) {
            GUICommons.setTextToLabel(lblAmount, "$" + (totalSale.subtract(new BigDecimal(partialPayTmp))));
        }
    }//GEN-LAST:event_txtPartialPayKeyReleased

    private void loadTitlesAndData() {
        final String[] titles = {"ID", "Nombre", "Total", "Pagos"};
        GUICommons.loadTitleOnTable(tblDebtors, titles, false);
        final var model = (DefaultTableModel) tblDebtors.getModel();
        model.setRowCount(0);
        items.stream().map(i -> (PojoDebtor) i).forEach(v -> {
            final Object[] row = {
                v.getIdDebtor(),
                v.getName(),
                v.getDebt()
            };
            model.addRow(row);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnSaveRegister;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblPartialPay;
    private javax.swing.JPanel pnlPayData;
    private javax.swing.JScrollPane tblContainer;
    private javax.swing.JTable tblDebtors;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPartialPay;
    // End of variables declaration//GEN-END:variables

}
