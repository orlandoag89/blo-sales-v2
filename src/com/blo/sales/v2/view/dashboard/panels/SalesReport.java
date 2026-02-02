package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.ISalesController;
import com.blo.sales.v2.controller.impl.SalesControllerImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.commons.CommonAlerts;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.plugins.sales.report.BloSalesV2SalesReportPlugin;
import com.blo.sales.v2.view.commons.GUILogger;
import com.blo.sales.v2.view.mappers.WrapperPojoSalesAndStockMapper;
import com.blo.sales.v2.view.pojos.PojoSaleAndProduct;
import com.blo.sales.v2.view.pojos.WrapperPojoSalesAndStock;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SalesReport extends javax.swing.JPanel {
    
    private static final GUILogger logger = GUILogger.getLogger(SalesReport.class.getName());
    
    private static final ISalesController salesController = SalesControllerImpl.getInstance();
    
    private static final WrapperPojoSalesAndStockMapper mapper = WrapperPojoSalesAndStockMapper.getInstance();
    
    private static final String[] titles = {"ID de venta", "ID producto", "Producto", "Cantidad en venta", "Precio", "Costo de venta", "Total", "Timestamp", "¿Por kg?"};

    public SalesReport() {
        initComponents();
        retrieveData();
    }
    
    private void retrieveData() {
        try {
            final var allSales = mapper.toOuter(salesController.retrieveAllSalesDetail());
            getTotal(allSales);
        } catch (BloSalesV2Exception ex) {
            logger.error(ex.getMessage());
            CommonAlerts.openError(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dtChooserInit = new com.toedter.calendar.JDateChooser();
        lblInit = new javax.swing.JLabel();
        lblEnd = new javax.swing.JLabel();
        dtChooserEnd = new com.toedter.calendar.JDateChooser();
        btnFilterNow = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSales = new javax.swing.JTable();
        lblTotalBruto = new javax.swing.JLabel();
        btnReportGenerator = new javax.swing.JButton();

        lblInit.setText("Fecha inicio");

        lblEnd.setText("Fecha fin");

        btnFilterNow.setText("Filtrar ahora");
        btnFilterNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterNowActionPerformed(evt);
            }
        });

        tblSales.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblSales);

        btnReportGenerator.setText("Descargar info");
        btnReportGenerator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportGeneratorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInit)
                    .addComponent(dtChooserInit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(dtChooserEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFilterNow))
                    .addComponent(lblEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTotalBruto, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                        .addGap(508, 508, 508))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnReportGenerator)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblInit)
                        .addComponent(lblEnd))
                    .addComponent(lblTotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtChooserInit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtChooserEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFilterNow)
                        .addComponent(btnReportGenerator)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFilterNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterNowActionPerformed
        final var initDate = GUICommons.getDateFromDateChooser(dtChooserInit);
        final var endDate = GUICommons.getDateFromDateChooser(dtChooserEnd);
        applyFilter(initDate, endDate);
    }//GEN-LAST:event_btnFilterNowActionPerformed

    private void btnReportGeneratorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportGeneratorActionPerformed
        try {
            final var model = (DefaultTableModel) tblSales.getModel();
            final var tmp = parserTableToLst(model);
            BloSalesV2SalesReportPlugin.createReport(tmp, getBrutalTotalFromLst(parserTableToLst(model)));
        } catch (BloSalesV2Exception ex) {
            logger.error(ex.getMessage());
            CommonAlerts.openError(ex.getMessage());
        }
    }//GEN-LAST:event_btnReportGeneratorActionPerformed

    public void applyFilter(String initDate, String endDate) {
        final var model = (DefaultTableModel) tblSales.getModel();
        final var sorter = new TableRowSorter<>(model);
        tblSales.setRowSorter(sorter);

        // se aplica filtro
        sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                final var dateSelected = entry.getStringValue(7).trim();
                if (dateSelected.isEmpty()) return false;
                try {
                    final var strDate = dateSelected.substring(0, 10);
                    if (!endDate.isBlank()) {
                        return strDate.compareTo(initDate) >= 0 && strDate.compareTo(endDate) <= 0;
                    } else {
                        return strDate.equals(initDate);
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    return false;
                }
            }
        });
        
        final var totalSale = getBrutalTotalFromLst(parserTableToLst(model));
        GUICommons.setTextToField(lblTotalBruto, "Total: $" + totalSale);
    }
    
    private WrapperPojoSalesAndStock parserTableToLst(DefaultTableModel model) {
        final var lst = new WrapperPojoSalesAndStock();
        final var items = new ArrayList<PojoSaleAndProduct>();
        PojoSaleAndProduct item = null;
        
        for (var i = 0; i < tblSales.getRowCount(); i++) {
            // Convertimos el índice de la vista al índice real del modelo
            final var modelIndex = tblSales.convertRowIndexToModel(i);

            item = new PojoSaleAndProduct();
            item.setIdSale(Long.parseLong(model.getValueAt(modelIndex, 0).toString()));
            item.setIdProduct(Long.parseLong(model.getValueAt(modelIndex, 1).toString()));
            item.setProduct(model.getValueAt(modelIndex, 2).toString());
            item.setQuantityOnSale(new BigDecimal(model.getValueAt(modelIndex, 3).toString()));
            item.setPrice(new BigDecimal(model.getValueAt(modelIndex, 4).toString()));
            item.setCostOfSale(new BigDecimal(model.getValueAt(modelIndex, 5).toString()));
            item.setTotalOnSale(new BigDecimal(model.getValueAt(modelIndex, 6).toString()));
            item.setTimestamp(model.getValueAt(modelIndex, 7).toString());
            item.setKg((boolean) model.getValueAt(modelIndex, 8));

            items.add(item);
        }
        lst.setSalesDetail(items);
        return lst;
    }
    
    private BigDecimal getTotal(WrapperPojoSalesAndStock wrapper) {
        GUICommons.loadTitleOnTable(tblSales, titles, false);
        if (wrapper.getSalesDetail() == null || wrapper.getSalesDetail().isEmpty()) {
            return BigDecimal.ZERO;
        }
        final var model = (DefaultTableModel) tblSales.getModel();
        model.setRowCount(0);
        wrapper.getSalesDetail().forEach(d -> {
                final Object[] row = {
                    d.getIdSale(),
                    d.getIdProduct(),
                    d.getProduct(),
                    d.getQuantityOnSale(),
                    d.getPrice(),
                    d.getCostOfSale(),
                    d.getTotalOnSale(),
                    d.getTimestamp(),
                    d.isKg()
                };
                model.addRow(row);
            });
        model.fireTableDataChanged(); 
        tblSales.repaint();
        tblSales.revalidate();
        return getBrutalTotalFromLst(wrapper);
    }
    
     /**
     * Metodo que regresa el total de una lista, filtra repetidos (idSale / totalOnSale)
     * @return 
     */
    private BigDecimal getBrutalTotalFromLst(WrapperPojoSalesAndStock wrapper) {
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
    private javax.swing.JButton btnReportGenerator;
    private com.toedter.calendar.JDateChooser dtChooserEnd;
    private com.toedter.calendar.JDateChooser dtChooserInit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEnd;
    private javax.swing.JLabel lblInit;
    private javax.swing.JLabel lblTotalBruto;
    private javax.swing.JTable tblSales;
    // End of variables declaration//GEN-END:variables
}
