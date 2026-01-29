package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.IDebtorsController;
import com.blo.sales.v2.controller.impl.DebtorsControllerImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.alerts.CommonAlerts;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.mappers.WrapperPojoDebtorsDetailsMapper;
import com.blo.sales.v2.view.pojos.PojoDebtorDetail;
import com.blo.sales.v2.view.pojos.PojoLoggedInUser;
import com.blo.sales.v2.view.pojos.WrapperPojoDebtorsDetails;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Debtors extends javax.swing.JPanel {
    
    private static final IDebtorsController debtors = DebtorsControllerImpl.getInstance();
    
    private static final WrapperPojoDebtorsDetailsMapper debtorsDetailsMapper = WrapperPojoDebtorsDetailsMapper.getInstance();
    
    private PojoLoggedInUser userData;
    
    private TableRowSorter<DefaultTableModel> sorter;
    
    /** deudor seleccionado para hacer operaciones */
    private PojoDebtorDetail debtorSelected;
    
    public Debtors(PojoLoggedInUser userData) {
        this.userData = userData;
        initComponents();
        try {
            final var debtorsFromDB = retrieveDebtorsDetails();
            loadDataAndTitles(debtorsFromDB);
            initFilter();
            GUICommons.addDoubleClickOnTable(tblDebtors, item -> {
                final var debtorDetail = 
                        debtorsFromDB.getDebtors().stream().
                            filter(d -> d.getIdDebtor() == Long.parseLong(item + ""))
                            .collect(Collectors.toList());
                GUICommons.setTextToField(nmbPay, BloSalesV2Utils.EMPTY_STRING);
                if (debtorDetail != null && !debtorDetail.isEmpty()) {
                    debtorSelected = debtorDetail.get(0);
                    areaPayments.setText(BloSalesV2Utils.EMPTY_STRING);
                    GUICommons.setTextToField(txtName, debtorSelected.getName());
                    GUICommons.setTextToField(lblDebt, "debe: $" + debtorSelected.getDebt());
                    Arrays.stream(debtorSelected.getPayments().split(BloSalesV2Utils.SEPARATOR_PAYMENTS)).forEach(p -> {
                        areaPayments.append(p);
                        areaPayments.append("\n");
                    });
                    final var model = new DefaultListModel<String>();
                    debtorDetail.forEach(d -> model.addElement(d.getProduct()));
                    lstProducts.setModel(model);
                }
            });
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Debtors.class.getName()).log(Level.SEVERE, null, ex);
            CommonAlerts.openError(ex.getMessage());
        }
    }
    
    private WrapperPojoDebtorsDetails retrieveDebtorsDetails() throws BloSalesV2Exception {
        return debtorsDetailsMapper.toOuter(debtors.getDebtorsDetails());
    }
    
    private void loadDataAndTitles(WrapperPojoDebtorsDetails debtors) throws BloSalesV2Exception {
        final String[] titles = {"ID", "Nombre", "Debe", "Timestamp"};
        GUICommons.loadTitleOnTable(tblDebtors, titles, false);
        final var model = (DefaultTableModel) tblDebtors.getModel();
        if (debtors.getDebtors() != null && !debtors.getDebtors().isEmpty()) {
            final var debtorsFilter = debtors.getDebtors().stream().collect(Collectors.toMap(
                    PojoDebtorDetail::getName, // Clave para identificar duplicados
                    obj -> obj,// El objeto en sí
                    (existente, reemplazo) -> existente // Si hay duplicado, se queda con el primero
            ))
                    .values()
                    .stream()
                    .collect(Collectors.toList());
            for(final var d: debtorsFilter) {
                final Object[] row = {
                    d.getIdDebtor(),
                    d.getName(),
                    d.getDebt(),
                    d.getTimestamp()
                };
                model.addRow(row);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDebtors = new javax.swing.JTable();
        txtSearchDebtor = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        nmbPay = new javax.swing.JTextField();
        btnPayall = new javax.swing.JButton();
        lblAddPartialPay = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        lblDebt = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaPayments = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstProducts = new javax.swing.JList<>();

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
        jScrollPane1.setViewportView(tblDebtors);

        txtSearchDebtor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchDebtorKeyReleased(evt);
            }
        });

        nmbPay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nmbPayKeyReleased(evt);
            }
        });

        btnPayall.setText("Pagar todo");
        btnPayall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayallActionPerformed(evt);
            }
        });

        lblAddPartialPay.setText("Abonar");

        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblAddPartialPay)
                        .addGap(307, 307, 307))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPayall))
                            .addComponent(nmbPay, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblDebt, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDebt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAddPartialPay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nmbPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPayall)
                    .addComponent(btnSave))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        areaPayments.setEditable(false);
        areaPayments.setColumns(20);
        areaPayments.setRows(5);
        jScrollPane2.setViewportView(areaPayments);

        jScrollPane3.setViewportView(lstProducts);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearchDebtor, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearchDebtor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchDebtorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchDebtorKeyReleased
        final var filter = txtSearchDebtor.getText();
        if (filter.trim().isBlank()) {
            sorter.setRowFilter(null);
        } else {
            // Al no poner un índice después del texto, busca en todas las columnas
            // "(?i)" sirve para ignorar mayúsculas y minúsculas
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filter));
        }
    }//GEN-LAST:event_txtSearchDebtorKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            final var dbt = GUICommons.getTextFromField(lblDebt, true);
            final var amount = new BigDecimal(dbt.substring(dbt.lastIndexOf("$") + 1));
            var payment = GUICommons.getNumberFromJText(nmbPay);
            if (payment.compareTo(amount) >= 0) {
                // se realiza el pago completo
                payment = debtorSelected.getDebt();
            }
            debtors.addPayment(payment, userData.getIdUser(), debtorSelected.getIdDebtor());
            loadDataAndTitles(retrieveDebtorsDetails());
            debtorSelected = null;
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Debtors.class.getName()).log(Level.SEVERE, null, ex);
            CommonAlerts.openError(ex.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void nmbPayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmbPayKeyReleased
        try {
            final var partialPay = GUICommons.getTextFromField(nmbPay, false);
            if (GUICommons.isEmptyFieldByKeyEvt(evt, partialPay.isBlank())) {
                GUICommons.setTextToField(lblDebt, "debe: $" + debtorSelected.getDebt());
            }
            if (
                    !partialPay.isBlank() &&
                    BloSalesV2Utils.validateTextWithPattern(BloSalesV2Utils.CURRENCY_REGEX, partialPay)
                ) {
                GUICommons.setTextToField(lblDebt, "debe: $" + (debtorSelected.getDebt().subtract(new BigDecimal(partialPay))));
            }
        } catch(BloSalesV2Exception e) {
        }
    }//GEN-LAST:event_nmbPayKeyReleased

    private void btnPayallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayallActionPerformed
        try {
            debtors.addPayment(debtorSelected.getDebt(), userData.getIdUser(), debtorSelected.getIdDebtor());
            debtorSelected = null;
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Debtors.class.getName()).log(Level.SEVERE, null, ex);
            CommonAlerts.openError(ex.getMessage());
        }
    }//GEN-LAST:event_btnPayallActionPerformed

    /** inicializa el filtro en la tabla */
    private void initFilter() {
        final var model = (DefaultTableModel) tblDebtors.getModel();
        sorter = new TableRowSorter<>(model);
        tblDebtors.setRowSorter(sorter);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaPayments;
    private javax.swing.JButton btnPayall;
    private javax.swing.JButton btnSave;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAddPartialPay;
    private javax.swing.JLabel lblDebt;
    private javax.swing.JList<String> lstProducts;
    private javax.swing.JTextField nmbPay;
    private javax.swing.JTable tblDebtors;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearchDebtor;
    // End of variables declaration//GEN-END:variables
}
