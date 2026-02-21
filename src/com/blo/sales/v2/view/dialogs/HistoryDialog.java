package com.blo.sales.v2.view.dialogs;

import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.pojos.WrapperPojoMovementsDetail;
import java.awt.Component;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class HistoryDialog extends javax.swing.JDialog {
    
    private static final String[] titles = {"ID Movimiento", "Tipo de movimiento", "Razón de movimiento", "Producto", "Cantidad", "Timestamp", "Usuario"};

    private WrapperPojoMovementsDetail history;
    public HistoryDialog(Component parent, String title, WrapperPojoMovementsDetail history) {
        super(SwingUtilities.getWindowAncestor(parent), title, ModalityType.APPLICATION_MODAL);
        this.history = history;
        initComponents();
        loadData();
    }
    
    private void loadData() {
        GUICommons.loadTitleOnTable(tblData, titles, false);
         final var model = (DefaultTableModel) tblData.getModel();
            model.setRowCount(0);
            history.getHistory().forEach(c -> {
                final Object[] row = {
                    c.getIdMovement(),
                    c.getType().name(),
                    c.getReason().name(),
                    c.getProduct(),
                    c.getQuantity(),
                    c.getTimestamp(),
                    c.getUsername()
                };
                model.addRow(row);
            });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblData.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblData);

        btnClose.setText("Cerrar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClose)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblData;
    // End of variables declaration//GEN-END:variables
}
