package com.blo.sales.v2.view.dialogs;

import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.pojos.PojoActiveCost;
import com.blo.sales.v2.view.pojos.PojoCashbox;
import com.blo.sales.v2.view.pojos.enums.ActivesCostsEnum;
import java.awt.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

public class CashboxDialog<T> extends javax.swing.JDialog {
    
    private List<PojoActiveCost> lstCosts;
        
    private DefaultListModel modelActives;
    
    private BigDecimal totalActives;
    
    private DefaultListModel modelPasives;
    
    private BigDecimal totalPasives;
    
    private BigDecimal totalActivesCosts;
    
    private Consumer<T> callback;

    public CashboxDialog(Component parent, String title, PojoCashbox cashboxData, Consumer<T> callback) {
        super(SwingUtilities.getWindowAncestor(parent), title, ModalityType.APPLICATION_MODAL);
        initComponents();
        this.callback = callback;
        lstCosts = new ArrayList<>();
        modelActives = new DefaultListModel();
        modelPasives = new DefaultListModel();
        totalActives = BigDecimal.ZERO;
        totalPasives = BigDecimal.ZERO;
        totalActivesCosts = cashboxData.getAmount();
        GUICommons.setTextToLabel(lblTotalToCashbox, "Total neto: " + cashboxData.getAmount());
        // lista de activos
        GUICommons.addDoubleClickOnListEvt(lstActives, item -> {
            final var indexSelected = lstActives.getSelectedIndex();
            if (indexSelected != -1) {
                    final var props = Arrays.asList(item.split(",")).stream().
                            map(i -> i.trim()).
                            collect(Collectors.toCollection(ArrayList::new));
                    // formato de item: item 0, concepto=Cuenta de ayer, monto=750, tipo=ACTIVO, completo=false
                    final var amount = new BigDecimal(props.get(2).split("=")[1].trim());
                    // resta en la cuenta de activos
                    totalActives = totalActives.subtract(amount);
                    GUICommons.setTextToLabel(lblActivesTotal, "Total activos: " + totalActives);
                    // se elimina del arreglo y de la lista
                    lstCosts.removeIf(i -> i.toString().equals(item));
                    modelActives.remove(indexSelected);
                    // se resta al total de activos a neto
                    totalActivesCosts = totalActivesCosts.subtract(amount);
                    GUICommons.setTextToLabel(lblTotalToCashbox, "Total neto: " + totalActivesCosts);
                }
        });
        // lista de pasivos
        GUICommons.addDoubleClickOnListEvt(lstPasives, item -> {
            final var indexSelected = lstPasives.getSelectedIndex();
            if (indexSelected != -1) {
                    final var props = Arrays.asList(item.split(",")).stream().
                            map(i -> i.trim()).
                            collect(Collectors.toCollection(ArrayList::new));
                    // formato de item: item 0, concepto=Cuenta de ayer, monto=750, tipo=ACTIVO, completo=false
                    final var amount = new BigDecimal(props.get(2).split("=")[1].trim());
                    // resta en la cuenta de activos
                    totalPasives = totalPasives.subtract(amount);
                    GUICommons.setTextToLabel(lblPasivesTotal, "Total costos: " + totalPasives);
                    // se elimina del arreglo y de la lista
                    lstCosts.removeIf(i -> i.toString().equals(item));
                    modelPasives.remove(indexSelected);
                    // se resta al total de activos a neto
                    totalActivesCosts = totalActivesCosts.add(amount);
                    GUICommons.setTextToLabel(lblTotalToCashbox, "Total neto: " + totalActivesCosts);
                }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCategoryName = new javax.swing.JTextField();
        cmbxType = new javax.swing.JComboBox<>();
        lblamountOnCashbox = new javax.swing.JLabel();
        lblAmount = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        btnContinue = new javax.swing.JButton();
        nmbAmount = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstActives = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstPasives = new javax.swing.JList<>();
        lblActivesTotal = new javax.swing.JLabel();
        lblPasivesTotal = new javax.swing.JLabel();
        lblTotalToCashbox = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cmbxType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 Activo", "2 Gasto" }));

        lblamountOnCashbox.setText("Monto total de ventas");

        lblAmount.setText("Monto");

        lblDescription.setText("Descripcion");

        btnContinue.setText("Continuar");
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(lstActives);

        jScrollPane2.setViewportView(lstPasives);

        lblActivesTotal.setText("Total activos");

        lblPasivesTotal.setText("Total Pasivos");

        lblTotalToCashbox.setText("Total neto");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblActivesTotal)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPasivesTotal)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTotalToCashbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActivesTotal)
                    .addComponent(lblPasivesTotal))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalToCashbox)
                    .addComponent(btnSave)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblamountOnCashbox, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCategoryName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                                .addComponent(btnContinue))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblAmount)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(nmbAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDescription)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblamountOnCashbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescription)
                    .addComponent(lblAmount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nmbAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(btnContinue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        this.callback.accept((T) lstCosts);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        try {
            final var concept = GUICommons.getTextFromJText(txtCategoryName);
            final var amount = GUICommons.getNumberFromJText(nmbAmount);
            final var type = GUICommons.getValueFromComboBox(cmbxType);
            // se agrega concepto a lista de activos pasivos
            var typeConcept = ActivesCostsEnum.ACTIVO;
            if (type.equals("2 Gasto")) {
                typeConcept = ActivesCostsEnum.PASIVO;
            }
            final var data = new PojoActiveCost();
            data.setAmount(amount);
            data.setConcept(concept);
            data.setComplete(false);
            data.setType(typeConcept);
            lstCosts.add(data);
            // guardar el concepto en las listas
            if (typeConcept.compareTo(ActivesCostsEnum.ACTIVO) == 0) {
                modelActives.addElement(data.toString());
                lstActives.setModel(modelActives);
                totalActives = totalActives.add(amount);
                GUICommons.setTextToLabel(lblActivesTotal, "Total activos: " + totalActives);
                totalActivesCosts = totalActivesCosts.add(amount);
                //cashboxData.setAmount(amountOnCashbox.add(amount));
            }
            if (typeConcept.compareTo(ActivesCostsEnum.PASIVO) == 0) {
                modelPasives.addElement(data.toString());
                lstPasives.setModel(modelPasives);
                totalPasives = totalPasives.add(amount);
                GUICommons.setTextToLabel(lblPasivesTotal, "Total pasivos: " + totalPasives);
                totalActivesCosts = totalActivesCosts.subtract(amount);
                //cashboxData.setAmount(amountOnCashbox.subtract(amount));
            }
            GUICommons.setTextToLabel(lblTotalToCashbox, "Total neto: " + totalActivesCosts);
            GUICommons.setTextToField(txtCategoryName, BloSalesV2Utils.EMPTY_STRING);
            GUICommons.setTextToField(nmbAmount, BloSalesV2Utils.EMPTY_STRING);
        } catch (BloSalesV2Exception ex) {
        }
    }//GEN-LAST:event_btnContinueActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbxType;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblActivesTotal;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblPasivesTotal;
    private javax.swing.JLabel lblTotalToCashbox;
    private javax.swing.JLabel lblamountOnCashbox;
    private javax.swing.JList<String> lstActives;
    private javax.swing.JList<String> lstPasives;
    private javax.swing.JTextField nmbAmount;
    private javax.swing.JTextField txtCategoryName;
    // End of variables declaration//GEN-END:variables
}
