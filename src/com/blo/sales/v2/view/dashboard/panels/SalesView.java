package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.ISalesController;
import com.blo.sales.v2.controller.impl.SalesControllerImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.mappers.WrapperPojoSalesAndStockMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class SalesView extends javax.swing.JPanel {

    private static final ISalesController salesController = SalesControllerImpl.getInstance();
    
    private static final WrapperPojoSalesAndStockMapper mapper = WrapperPojoSalesAndStockMapper.getInstance();
        
    public SalesView() {
        initComponents();
        loadData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSalesDetail = new javax.swing.JTable();

        tblSalesDetail.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblSalesDetail);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadData() {
        try {
            final var salesDetail = mapper.toOuter(salesController.retrieveAllSalesDetail());
            final String[] titles = {"ID de venta", "ID producto", "Producto", "Cantidad en venta", "Total", "Timestamp"};
            GUICommons.loadTitleOnTable(tblSalesDetail, titles, false);
            final var model = (DefaultTableModel) tblSalesDetail.getModel();
            model.setRowCount(0);
            salesDetail.getSalesDetail().forEach(d -> {
                    final Object[] row = {
                        d.getIdSale(),
                        d.getIdProduct(),
                        d.getProduct(),
                        d.getQuantityOnSale(),
                        d.getTotalOnSale(),
                        d.getTimestamp()
                    };
                    model.addRow(row);
                });
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(SalesView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSalesDetail;
    // End of variables declaration//GEN-END:variables
}
