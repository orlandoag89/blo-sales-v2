package com.blo.sales.v2.view.windows.dashboard.panels;

import com.blo.sales.v2.controller.ICategoriesController;
import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.impl.CategoriesControllerImpl;
import com.blo.sales.v2.controller.impl.ProductsControllerImpl;
import com.blo.sales.v2.controller.pojos.PojoIntProduct;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.windows.commons.GUICommons;
import com.blo.sales.v2.view.windows.mappers.WrapperPojoProductsMapper;
import com.blo.sales.v2.view.windows.pojos.PojoProduct;
import com.blo.sales.v2.view.windows.pojos.WrapperPojoProducts;
import com.blo.sales.v2.view.windows.pojos.enums.ReasonsEnum;
import com.blo.sales.v2.view.windows.pojos.enums.RolesEnum;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AllProducts extends javax.swing.JPanel {

    private RolesEnum rol;
    
    private IProductsController products;
    
    private ICategoriesController categories;
    
    private TableRowSorter<DefaultTableModel> sorter;
    
    private BigDecimal currentQuantity;
    
    private WrapperPojoProductsMapper productsMapper;
    
    public AllProducts(RolesEnum rol) {        
        try {
            this.rol = rol;
            products = ProductsControllerImpl.getInstance();
            categories = CategoriesControllerImpl.getInstance();
            productsMapper = WrapperPojoProductsMapper.getInstance();
            initComponents();
            
            final var productsData = productsMapper.toOuter(this.products.getAllProducts());
            loadTitlesAndData(productsData.getProducts());
            initFilter();
            initPanelManagement();
            /** selecciona una fila */
            GUICommons.addDoubleClickOnTable(tblProducts, (Integer id) -> {
                pnlManageProduct.setVisible(true);
                final var productSelected = 
                        productsData.getProducts().stream().filter(p -> p.getIdProduct() == id).findFirst().orElse(null);
                if (productSelected != null) {
                    currentQuantity = productSelected.getQuantity();
                    GUICommons.setTextToField(txtName, productSelected.getProduct());
                    GUICommons.setTextToField(txtBarCode, productSelected.getBarCode());
                    GUICommons.setTextToField(nmbCostOfSale, productSelected.getCostOfSale() + "");
                    GUICommons.setTextToField(nmbPrice, productSelected.getPrice() + "");
                    GUICommons.setTextToField(nmbQuantity, productSelected.getQuantity() + "");
                }
            });
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(AllProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        txtSearcher = new javax.swing.JTextField();
        pnlManageProduct = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        nmbQuantity = new javax.swing.JTextField();
        nmbCostOfSale = new javax.swing.JTextField();
        nmbPrice = new javax.swing.JTextField();
        txtBarCode = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lstReason = new javax.swing.JComboBox<>();

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

        nmbQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nmbQuantityKeyReleased(evt);
            }
        });

        btnSave.setText("Guardar cambios");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lstReason.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vendidos", "Perdido", "Reabastecimiento" }));

        javax.swing.GroupLayout pnlManageProductLayout = new javax.swing.GroupLayout(pnlManageProduct);
        pnlManageProduct.setLayout(pnlManageProductLayout);
        pnlManageProductLayout.setHorizontalGroup(
            pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlManageProductLayout.createSequentialGroup()
                        .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlManageProductLayout.createSequentialGroup()
                                .addComponent(nmbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lstReason, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlManageProductLayout.createSequentialGroup()
                                .addComponent(nmbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nmbCostOfSale, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlManageProductLayout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel)))
                .addContainerGap(510, Short.MAX_VALUE))
        );
        pnlManageProductLayout.setVerticalGroup(
            pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBarCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nmbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nmbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nmbCostOfSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lstReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addContainerGap(46, Short.MAX_VALUE))
        );

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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlManageProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearcher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlManageProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
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

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        initPanelManagement();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            final var newData = new PojoProduct();
            newData.setProduct(GUICommons.getTextFromJText(txtName));
            newData.setBarCode(GUICommons.getTextFromJText(txtBarCode));
            newData.setCostOfSale(GUICommons.getNumberFromJText(nmbCostOfSale));
            newData.setPrice(GUICommons.getNumberFromJText(nmbPrice));
            if (lstReason.isVisible()) {
                newData.setQuantity(GUICommons.getNumberFromJText(nmbQuantity));
                ReasonsEnum reasonEnum;
                final var reason = GUICommons.getValueFromComboBox(lstReason);
                switch (reason) {
                    case "Vendido":
                        reasonEnum = ReasonsEnum.SALE;
                        break;
                    case "Perdido":
                        reasonEnum = ReasonsEnum.LOST;
                        break;
                    case "Reabastecimiento":
                        reasonEnum = ReasonsEnum.REPLENISHMENT;
                        break;
                }
            }
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(AllProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void nmbQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmbQuantityKeyReleased
        /** activa o desactiva el combo de acuerdo a la cantidad de producto */
        lstReason.setVisible(true);
        final var tmpQuantity = nmbQuantity.getText();
        if (tmpQuantity.trim().isBlank()) {
            return;
        }
        final var quantity = new BigDecimal(nmbQuantity.getText().trim());
        /** desactiva el combo si se modifico pero la cantidad es la misma */
        if (currentQuantity.compareTo(quantity) == 0) {
            lstReason.setVisible(false);
        }
    }//GEN-LAST:event_nmbQuantityKeyReleased

    private void loadTitlesAndData(List<PojoProduct> products) {
        try {
            final var categories = this.categories.getAllCategories();
            if (rol.equals(RolesEnum.ROOT)) {
                final String[] titles = {"ID", "Codigo de barras", "Producto", "Cantidad en existencia", "Costo de venta", "¿Por kg?", "Categoria"};
                GUICommons.loadTitleOnTable(tblProducts, titles, false);
                final var model = (DefaultTableModel) tblProducts.getModel();
                model.setRowCount(0);
                products.forEach(p -> {
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
    
    /** reinicia los campos */
    private void initPanelManagement() {
        pnlManageProduct.setVisible(false);
        currentQuantity = new BigDecimal(BigInteger.ZERO);
        /** este check estara oculto hasta que se de cambie la propiedad de cantidad */
        lstReason.setVisible(false);
        GUICommons.setTextToField(txtName, BloSalesV2Utils.EMPTY_STRING);
        GUICommons.setTextToField(txtBarCode, BloSalesV2Utils.EMPTY_STRING);
        GUICommons.setTextToField(nmbCostOfSale, BloSalesV2Utils.EMPTY_STRING);
        GUICommons.setTextToField(nmbPrice, BloSalesV2Utils.EMPTY_STRING);
        GUICommons.setTextToField(nmbQuantity, BloSalesV2Utils.EMPTY_STRING);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> lstReason;
    private javax.swing.JTextField nmbCostOfSale;
    private javax.swing.JTextField nmbPrice;
    private javax.swing.JTextField nmbQuantity;
    private javax.swing.JPanel pnlManageProduct;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtBarCode;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearcher;
    // End of variables declaration//GEN-END:variables
}
