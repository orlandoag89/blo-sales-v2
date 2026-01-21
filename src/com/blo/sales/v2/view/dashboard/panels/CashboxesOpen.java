package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.ICashboxController;
import com.blo.sales.v2.controller.impl.CashboxControllerImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.dialogs.CashboxDialog;
import com.blo.sales.v2.view.dialogs.CashboxDialog;
import com.blo.sales.v2.view.dialogs.DebtorsDialog;
import com.blo.sales.v2.view.mappers.PojoCashboxMapper;
import com.blo.sales.v2.view.mappers.WrapperPojoCashboxesMapper;
import com.blo.sales.v2.view.pojos.PojoActiveCost;
import com.blo.sales.v2.view.pojos.PojoCashbox;
import com.blo.sales.v2.view.pojos.PojoLoggedInUser;
import com.blo.sales.v2.view.pojos.WrapperPojoCashboxes;
import com.blo.sales.v2.view.pojos.enums.CashboxStatusEnum;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class CashboxesOpen extends javax.swing.JPanel {
    
    private static final ICashboxController cashboxController = CashboxControllerImpl.getInstance();
    
    private static final PojoCashboxMapper mapper = PojoCashboxMapper.getInstance();
    
    private PojoLoggedInUser userData;
    
    private PojoCashbox openCashbox;
    
    public CashboxesOpen(PojoLoggedInUser userData) {
        this.userData = userData;
        initComponents();
        try {
            openCashbox = mapper.toOuter(cashboxController.getOpenCashbox());
            loadDataAndTitles(openCashbox);
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(CashboxesOpen.class.getName()).log(Level.SEVERE, null, ex);
        }
       /* try {
            final var cashboxes = retrieveCashboxes();
            loadDataAndTitles(cashboxes);
            openCashbox = 
                    cashboxes.getCashboxes().stream().filter(c -> c.getStatus().compareTo(CashboxStatusEnum.OPEN) == 0).findFirst().orElse(null);
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(CashboxesOpen.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    /** metodo que recupera las cajas de dinero cerradas */
    /*private WrapperPojoCashboxes retrieveCashboxes() throws BloSalesV2Exception {
        return mapper.toOuter(cashboxController.getOpenCashbox());
    }*/
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCashboxes = new javax.swing.JTable();
        btnCloseNow = new javax.swing.JButton();

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

        btnCloseNow.setText("Cerrar ahora");
        btnCloseNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseNowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCloseNow)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCloseNow)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseNowActionPerformed
        final var cashboxDialog = new CashboxDialog<>(
                this,
                "Cerrando caja de dinero",
                openCashbox,
                (List<PojoActiveCost> items) -> {
                    items.forEach(System.out::println);
                }
        );
        cashboxDialog.setVisible(true);
    }//GEN-LAST:event_btnCloseNowActionPerformed

     private void loadDataAndTitles(PojoCashbox cashbox) throws BloSalesV2Exception {
        final String[] titles = {"ID", "Monto", "Gestionada por", "Fecha"};
        GUICommons.loadTitleOnTable(tblCashboxes, titles, false);
        final var model = (DefaultTableModel) tblCashboxes.getModel();
        final Object[] row = {
            cashbox.getIdCashbox(),
            cashbox.getAmount(),
            cashbox.getUserFrom(),
            cashbox.getTimestamp()
        };
        model.addRow(row);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCloseNow;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCashboxes;
    // End of variables declaration//GEN-END:variables
}
