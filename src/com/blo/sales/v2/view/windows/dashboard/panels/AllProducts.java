package com.blo.sales.v2.view.windows.dashboard.panels;

import com.blo.sales.v2.controller.ICategoriesController;
import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.impl.CategoriesControllerImpl;
import com.blo.sales.v2.controller.impl.ProductsControllerImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.windows.commons.GUICommons;
import com.blo.sales.v2.view.windows.pojos.enums.RolesEnum;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AllProducts extends javax.swing.JPanel {

    private RolesEnum rol;
    
    private IProductsController products;
    
    private ICategoriesController categories;
    
    private TableRowSorter<DefaultTableModel> sorter;
    
    public AllProducts(RolesEnum rol) {
        this.rol = rol;
        products = new ProductsControllerImpl();
        categories = new CategoriesControllerImpl();
        initComponents();
        loadTitles();
        initFilter();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        txtSearcher = new javax.swing.JTextField();

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblProducts);

        txtSearcher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearcherKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 989, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearcher, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearcher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearcherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearcherKeyReleased
        final var filter = txtSearcher.getText();
        if (filter.trim().isBlank()) {
            // Si el buscador está vacío, mostramos todas las filas
            sorter.setRowFilter(null);
        } else {
            // Al no poner un índice después del texto, busca en todas las columnas
            // "(?i)" sirve para ignorar mayúsculas y minúsculas
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filter));
        }
    }//GEN-LAST:event_txtSearcherKeyReleased

    private void loadTitles() {
        try {
            final var categories = this.categories.getAllCategories();
            if (rol.equals(RolesEnum.ROOT)) {
                final String[] titles = {"ID", "Codigo de barras", "Producto", "Cantidad en existencia", "Costo de venta", "¿Por kg?", "Categoria"};
                GUICommons.loadTitleOnTable(tblProducts, titles);
                final var model = (DefaultTableModel) tblProducts.getModel();
                model.setRowCount(0);
                products.getAllProducts().getProducts().forEach(p -> {
                    /** filtro para buscar nombre de categorias */
                    final var category = categories.getCategories().stream().filter(c -> c.getIdCategory() == p.getFkCategory()).findFirst().get();
                    final Object[] row = {
                        p.getIdProduct(),
                        p.getBarCode(),
                        p.getProduct(),
                        p.getQuantity(),
                        p.getCostOfSale(),
                        p.isKg() ? "SI": "NO",
                        category.getCategory()
                    };
                    model.addRow(row);
                });
            }
        } catch (final BloSalesV2Exception e) { }
    }
    
    /** inicializa el filtro en la tabla */
    private void initFilter() {
        final var model = (DefaultTableModel) tblProducts.getModel();
        sorter = new TableRowSorter<>(model);
        tblProducts.setRowSorter(sorter);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtSearcher;
    // End of variables declaration//GEN-END:variables
}
