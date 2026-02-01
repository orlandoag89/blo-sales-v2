package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.ISalesController;
import com.blo.sales.v2.controller.impl.SalesControllerImpl;
import com.blo.sales.v2.controller.pojos.enums.SalesStatusIntEnum;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.alerts.CommonAlerts;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.mappers.WrapperPojoSalesAndStockMapper;
import com.blo.sales.v2.view.pojos.PojoSaleAndProduct;
import com.blo.sales.v2.view.pojos.WrapperPojoSalesAndStock;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SalesViewer extends javax.swing.JPanel {

    private static final ISalesController salesController = SalesControllerImpl.getInstance();
    
    private static final WrapperPojoSalesAndStockMapper mapper = WrapperPojoSalesAndStockMapper.getInstance();
    
    private static final String[] titles = {"ID de venta", "ID producto", "Producto", "Cantidad en venta", "Total", "Timestamp"};
    
    private boolean todayLoaded = false;

    private boolean allSalesLoaded = false;

    public SalesViewer() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlToday = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSalesToday = new javax.swing.JTable();
        lblTotalToday = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSalesDetail = new javax.swing.JTable();
        pnlFilter = new javax.swing.JPanel();
        dtChooserInit = new com.toedter.calendar.JDateChooser();
        lblInitDate = new javax.swing.JLabel();
        lblEndDate = new javax.swing.JLabel();
        dtChooserEnd = new com.toedter.calendar.JDateChooser();
        btnFilterNow = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        lblTotalSale = new javax.swing.JLabel();

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        tblSalesToday.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblSalesToday);

        lblTotalToday.setText("Hoy: $0.00");

        javax.swing.GroupLayout pnlTodayLayout = new javax.swing.GroupLayout(pnlToday);
        pnlToday.setLayout(pnlTodayLayout);
        pnlTodayLayout.setHorizontalGroup(
            pnlTodayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTodayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(lblTotalToday)
                .addContainerGap())
        );
        pnlTodayLayout.setVerticalGroup(
            pnlTodayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTodayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTodayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTodayLayout.createSequentialGroup()
                        .addComponent(lblTotalToday)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ventas de hoy", pnlToday);

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

        lblInitDate.setText("Fecha de inicio");

        lblEndDate.setText("Fecha fin (opcional)");

        btnFilterNow.setText("Filtrar ahora");
        btnFilterNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterNowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFilterLayout = new javax.swing.GroupLayout(pnlFilter);
        pnlFilter.setLayout(pnlFilterLayout);
        pnlFilterLayout.setHorizontalGroup(
            pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtChooserInit, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInitDate))
                .addGap(18, 18, 18)
                .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEndDate)
                    .addGroup(pnlFilterLayout.createSequentialGroup()
                        .addComponent(dtChooserEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFilterNow)
                        .addGap(18, 18, 18)
                        .addComponent(lblTotalSale, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(235, Short.MAX_VALUE))
        );
        pnlFilterLayout.setVerticalGroup(
            pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInitDate)
                    .addComponent(lblEndDate))
                .addGap(8, 8, 8)
                .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTotalSale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dtChooserInit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dtChooserEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFilterNow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                    .addComponent(pnlFilter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(pnlFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Todas las ventas", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        try {
            int selectedIndex = jTabbedPane1.getSelectedIndex();
            if (selectedIndex == 0 && !todayLoaded) { // Pestaña "pnlToday"
                final var salesToday = mapper.toOuter(salesController.retrieveSalesByStatus(SalesStatusIntEnum.CLOSE));
                final var total = loadData(salesToday, tblSalesToday);
                GUICommons.setTextToField(lblTotalToday, "Total hoy: $" + total);
                todayLoaded = true;
            } else if (selectedIndex == 1 && !allSalesLoaded) { // Pestaña "jPanel2"
                final var allSales = mapper.toOuter(salesController.retrieveAllSalesDetail());
                loadData(allSales, tblSalesDetail); 
                allSalesLoaded = true;
            }
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(SalesViewer.class.getName()).log(Level.SEVERE, null, ex);
            CommonAlerts.openError(ex.getMessage());
        } 
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void btnFilterNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterNowActionPerformed
        final var initDate = GUICommons.getDateFromDateChooser(dtChooserInit);
        final var endDate = GUICommons.getDateFromDateChooser(dtChooserEnd);
        aplicarFiltroFecha(initDate, endDate);
    }//GEN-LAST:event_btnFilterNowActionPerformed
    
    public void aplicarFiltroFecha(String initDate, String endDate) {
        // 1. Validar que al menos la fecha de inicio exista
        if (initDate.isBlank()) {
            tblSalesDetail.setRowSorter(null);
            return;
        }

        final var model = (DefaultTableModel) tblSalesDetail.getModel();
        final var sorter = new TableRowSorter<>(model);
        tblSalesDetail.setRowSorter(sorter);
        final var lst = new WrapperPojoSalesAndStock();
        final var items = new ArrayList<PojoSaleAndProduct>();

        sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                // Obtenemos el valor de la columna 6 (índice 5) y limpiamos espacios
                final var dateSelected = entry.getStringValue(5).trim();
                if (dateSelected.isEmpty()) {
                    return false;
                }

                try {
                    // Extraemos "yyyy-MM-dd" del timestamp largo
                    final var strDate = dateSelected.substring(0, 10);
                    // CASO A: Rango de fechas (Inicio y Fin presentes)
                    if (!endDate.isBlank()) {
                        final var isRange = strDate.compareTo(initDate) >= 0 && 
                               strDate.compareTo(endDate) <= 0;
                        if (isRange) {
                            final var item = new PojoSaleAndProduct();
                            item.setIdSale(Long.parseLong(entry.getStringValue(0)));
                            item.setTotalOnSale(new BigDecimal(entry.getStringValue(4)));
                            items.add(item);
                        }
                        return isRange;
                    }
                    // CASO B: Fecha única (Solo inicio)
                    else {
                        final var isDate = strDate.equals(initDate);
                        if (isDate) {
                            final var item = new PojoSaleAndProduct();
                            item.setIdSale(Long.parseLong(entry.getStringValue(0)));
                            item.setTotalOnSale(new BigDecimal(entry.getStringValue(4)));
                            items.add(item);
                        }
                        return isDate;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        });
        lst.setSalesDetail(items);
        final var totalSale = getTotalFromLst(lst);
        GUICommons.setTextToField(lblTotalSale, "Total: $" + totalSale);
    }

    private BigDecimal loadData(WrapperPojoSalesAndStock wrapper, JTable table) {
        GUICommons.loadTitleOnTable(table, titles, false);
        if (wrapper.getSalesDetail() == null || wrapper.getSalesDetail().isEmpty()) {
            return BigDecimal.ZERO;
        }
        final var model = (DefaultTableModel) table.getModel();
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
        table.repaint();
        table.revalidate();
        return getTotalFromLst(wrapper);
    }

    /**
     * Metodo que regresa el total de una lista, filtra repetidos (idSale / totalOnSale)
     * @return 
     */
    private BigDecimal getTotalFromLst(WrapperPojoSalesAndStock wrapper) {
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFilterNow;
    private com.toedter.calendar.JDateChooser dtChooserEnd;
    private com.toedter.calendar.JDateChooser dtChooserInit;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblInitDate;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalSale;
    private javax.swing.JLabel lblTotalToday;
    private javax.swing.JPanel pnlFilter;
    private javax.swing.JPanel pnlToday;
    private javax.swing.JTable tblSalesDetail;
    private javax.swing.JTable tblSalesToday;
    // End of variables declaration//GEN-END:variables
}
