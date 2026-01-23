package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.ICashboxController;
import com.blo.sales.v2.controller.impl.CashboxControllerImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.mappers.WrapperPojoCashboxesDetailsMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class AllCashboxes extends javax.swing.JPanel {
    
    private static final ICashboxController controller = CashboxControllerImpl.getInstance();
    
    private static final WrapperPojoCashboxesDetailsMapper mapper = WrapperPojoCashboxesDetailsMapper.getInstance();

    public AllCashboxes() {
        initComponents();
        cashboxesOnTable();
    }
    
    private void cashboxesOnTable() {
        try {
            final String[] titles = {"ID", "Monto", "Status", "Conceptos", "Tipo de concepto", "Timestamp"};
            final var cashboxes = mapper.toOuter(controller.getCashboxesDetail());
            GUICommons.loadTitleOnTable(tblCashboxes, titles, false);
            final var model = (DefaultTableModel) tblCashboxes.getModel();
            model.setRowCount(0);
            cashboxes.getCashboxesInfo().forEach(c -> {
                final Object[] row = {
                    c.getIdCashbox(),
                    c.getAmount(),
                    c.getStatus().name(),
                    c.getConcept(),
                    c.getType(),
                    c.getTimestamp()
                };
                model.addRow(row);
            });
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(AllCashboxes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCashboxes = new javax.swing.JTable();

        tblCashboxes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblCashboxes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCashboxes;
    // End of variables declaration//GEN-END:variables
}
