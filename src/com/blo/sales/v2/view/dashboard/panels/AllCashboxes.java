package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.ICashboxController;
import com.blo.sales.v2.controller.impl.CashboxControllerImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.alerts.CommonAlerts;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.mappers.WrapperPojoCashboxesDetailsMapper;
import com.blo.sales.v2.view.pojos.PojoCashboxDetail;
import com.blo.sales.v2.view.pojos.WrapperPojoCashboxesDetails;
import com.blo.sales.v2.view.pojos.enums.ActivesCostsEnum;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

public class AllCashboxes extends javax.swing.JPanel {
    
    private static final ICashboxController controller = CashboxControllerImpl.getInstance();
    
    private static final WrapperPojoCashboxesDetailsMapper mapper = WrapperPojoCashboxesDetailsMapper.getInstance();

    public AllCashboxes() {
        initComponents();
        try {
            final var cashboxes = mapper.toOuter(controller.getCashboxesDetail());
            if (cashboxes.getCashboxesInfo() != null && !cashboxes.getCashboxesInfo().isEmpty()) {
                cashboxesOnTable(cashboxes);
                GUICommons.addDoubleClickOnTable(tblCashboxes, id -> {
                    final var cashboxFound = 
                            cashboxes.getCashboxesInfo().stream().filter(c -> c.getIdCashbox() == (long) id).collect(Collectors.toList());
                    
                    final var modeloListaActivos = new DefaultListModel<String>();
                    final var modeloListaPaivos = new DefaultListModel<String>();
                    getActivesFilters(cashboxFound, ActivesCostsEnum.ACTIVO).
                            forEach(i -> modeloListaActivos.addElement(i.getConcept() + "$: " + i.getConceptAmount()));
                    getActivesFilters(cashboxFound, ActivesCostsEnum.PASIVO).
                            forEach(i -> modeloListaPaivos.addElement(i.getConcept() + "$: " + i.getConceptAmount()));
                    lstActives.setModel(modeloListaActivos);
                    lstCosts.setModel(modeloListaPaivos);
                });
            }
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(AllCashboxes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private List<PojoCashboxDetail> getActivesFilters(List<PojoCashboxDetail> lst, ActivesCostsEnum type) {
        return lst.stream().filter(c -> c.getType().compareTo(type) == 0).collect(Collectors.toList());
    }
    
    private void cashboxesOnTable(WrapperPojoCashboxesDetails cashboxes) {
        final String[] titles = {"ID", "Monto", "Status", "Timestamp"};
        GUICommons.loadTitleOnTable(tblCashboxes, titles, false);
            final var cashboxesFilter = cashboxes.getCashboxesInfo().stream().collect(Collectors.toMap(
                    PojoCashboxDetail::getIdCashbox,
                    obj -> obj,
                    (existente, reemplazo) -> existente
            )).values()
                    .stream()
                    .collect(Collectors.toList());
            final var model = (DefaultTableModel) tblCashboxes.getModel();
            model.setRowCount(0);
            cashboxesFilter.forEach(c -> {
                final Object[] row = {
                    c.getIdCashbox(),
                    c.getAmount(),
                    c.getStatus().name(),
                    c.getTimestamp()
                };
                model.addRow(row);
            });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCashboxes = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstActives = new javax.swing.JList<>();
        lblActives = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstCosts = new javax.swing.JList<>();
        lblCosts = new javax.swing.JLabel();

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

        jScrollPane2.setViewportView(lstActives);

        lblActives.setText("Activos");

        jScrollPane3.setViewportView(lstCosts);

        lblCosts.setText("Gastos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblActives))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCosts)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActives)
                    .addComponent(lblCosts))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblActives;
    private javax.swing.JLabel lblCosts;
    private javax.swing.JList<String> lstActives;
    private javax.swing.JList<String> lstCosts;
    private javax.swing.JTable tblCashboxes;
    // End of variables declaration//GEN-END:variables
}
