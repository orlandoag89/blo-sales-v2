package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.ISalesController;
import com.blo.sales.v2.controller.impl.SalesControllerImpl;
import com.blo.sales.v2.controller.pojos.enums.SalesStatusIntEnum;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.commons.CommonAlerts;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.commons.GUILogger;
import com.blo.sales.v2.view.mappers.WrapperPojoSalesAndStockMapper;
import com.blo.sales.v2.view.pojos.PojoSaleAndProduct;
import com.blo.sales.v2.view.pojos.WrapperPojoSalesAndStock;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;

public class SalesToday extends javax.swing.JPanel {
    
    private static final GUILogger logger = GUILogger.getLogger(SalesToday.class.getName());
    
    private static final ISalesController salesController = SalesControllerImpl.getInstance();
    
    private static final WrapperPojoSalesAndStockMapper mapper = WrapperPojoSalesAndStockMapper.getInstance();

    private static final String[] titles = {"ID de venta", "ID producto", "Producto", "Cantidad en venta", "Total", "Timestamp"};
    
    public SalesToday() {
        initComponents();
        loadData();
    }
    
    private void loadData() {
        try {
            final var salesToday = mapper.toOuter(salesController.retrieveSalesByStatus(SalesStatusIntEnum.CLOSE));
            final var total = getTotal(salesToday);
            GUICommons.setTextToField(lblTotal, "Total: $" + total);
        } catch (BloSalesV2Exception ex) {
            logger.error(ex.getMessage());
            CommonAlerts.openError(ex.getMessage());
        }
    }
    
     private BigDecimal getTotal(WrapperPojoSalesAndStock wrapper) {
        GUICommons.loadTitleOnTable(tblSummary, titles, false);
        if (wrapper.getSalesDetail() == null || wrapper.getSalesDetail().isEmpty()) {
            return BigDecimal.ZERO;
        }
        final var model = (DefaultTableModel) tblSummary.getModel();
        model.setRowCount(0);
        wrapper.getSalesDetail().forEach(d -> {
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
        model.fireTableDataChanged(); 
        tblSummary.repaint();
        tblSummary.revalidate();
        return doSumFrom(wrapper);
    }
     
      /**
     * Metodo que regresa el total de una lista, filtra repetidos (idSale / totalOnSale)
     * @return 
     */
    private BigDecimal doSumFrom(WrapperPojoSalesAndStock wrapper) {
        return wrapper.getSalesDetail().stream().collect(Collectors.toMap(
                        PojoSaleAndProduct::getIdSale,
                        obj -> obj,
                        (existente, reemplazo) -> existente // Si hay duplicado, se queda con el primero
                    )).
                        values().
                        stream().
                        map(PojoSaleAndProduct::getTotalOnSale).
                        reduce(BigDecimal.ZERO, (acc, curr) -> acc.add(curr));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSummary = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();

        tblSummary.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblSummary);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblSummary;
    // End of variables declaration//GEN-END:variables
}
