package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.ICashboxController;
import com.blo.sales.v2.controller.impl.CashboxControllerImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.mappers.WrapperPojoCashboxesMapper;
import com.blo.sales.v2.view.pojos.PojoCashbox;
import com.blo.sales.v2.view.pojos.PojoLoggedInUser;
import com.blo.sales.v2.view.pojos.WrapperPojoCashboxes;
import com.blo.sales.v2.view.pojos.enums.CashboxStatusEnum;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Cashboxes extends javax.swing.JPanel {
    
    private static final ICashboxController cashboxController = CashboxControllerImpl.getInstance();
    
    private static final WrapperPojoCashboxesMapper mapper = WrapperPojoCashboxesMapper.getInstance();
    
    private PojoLoggedInUser userData;
    
    private PojoCashbox openCashbox;
    
    public Cashboxes(PojoLoggedInUser userData) {
        this.userData = userData;
        initComponents();
        try {
            final var cashboxes = retrieveCashboxes();
            loadDataAndTitles(cashboxes);
            openCashbox = 
                    cashboxes.getCashboxes().stream().filter(c -> c.getStatus().compareTo(CashboxStatusEnum.OPEN) == 0).findFirst().orElse(null);
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(Cashboxes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** metodo que recupera las cajas de dinero cerradas */
    private WrapperPojoCashboxes retrieveCashboxes() throws BloSalesV2Exception {
        return mapper.toOuter(cashboxController.getAllCashboxes());
    }

    private void loadDataAndTitles(WrapperPojoCashboxes boxes) throws BloSalesV2Exception {
        final String[] titles = {"ID", "Monto", "Gestionada por", "Fecha", "¿Cerrada?"};
        GUICommons.loadTitleOnTable(tblCashboxes, titles, false);
        final var model = (DefaultTableModel) tblCashboxes.getModel();
        boxes.getCashboxes().forEach(d -> {
            final Object[] row = {
                d.getIdCashbox(),
                d.getAmount(),
                d.getUserFrom(),
                d.getTimestamp(),
                d.getStatus().compareTo(CashboxStatusEnum.CLOSE) == 0 ? "SI": "NO"
            };
            model.addRow(row);
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCashboxes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
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

        jLabel1.setText("jLabel1");

        btnCloseNow.setText("Cerrar ahora");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCloseNow)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCloseNow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCloseNow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCashboxes;
    // End of variables declaration//GEN-END:variables
}
