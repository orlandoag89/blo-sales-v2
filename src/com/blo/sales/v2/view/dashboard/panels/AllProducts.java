package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.ICategoriesController;
import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.impl.CategoriesControllerImpl;
import com.blo.sales.v2.controller.impl.ProductsControllerImpl;
import com.blo.sales.v2.controller.pojos.enums.ReasonsIntEnum;
import com.blo.sales.v2.controller.pojos.enums.TypesIntEnum;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.commons.GUILogger;
import com.blo.sales.v2.view.mappers.ProductMapper;
import com.blo.sales.v2.view.mappers.WrapperPojoCategoriesMapper;
import com.blo.sales.v2.view.mappers.WrapperPojoProductsMapper;
import com.blo.sales.v2.view.pojos.PojoLoggedInUser;
import com.blo.sales.v2.view.pojos.PojoProduct;
import com.blo.sales.v2.view.pojos.enums.ReasonsEnum;
import com.blo.sales.v2.view.pojos.enums.RolesEnum;
import com.blo.sales.v2.view.pojos.enums.TypesEnum;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AllProducts extends javax.swing.JPanel {
    
    private static final GUILogger logger = GUILogger.getLogger(AllProducts.class.getName());

    private PojoLoggedInUser userData;
    
    private IProductsController productsController;
    
    private ICategoriesController categories;
    
    private TableRowSorter<DefaultTableModel> sorter;
    
    private BigDecimal currentQuantity;
    
    private WrapperPojoProductsMapper productsMapper;
    
    private ProductMapper productMapper;
    
    private WrapperPojoCategoriesMapper categoriesMapper;
    
    public AllProducts(PojoLoggedInUser userData) {
        this.userData = userData;
        productsController = ProductsControllerImpl.getInstance();
        categories = CategoriesControllerImpl.getInstance();
        productsMapper = WrapperPojoProductsMapper.getInstance();
        productMapper = ProductMapper.getInstance();
        categoriesMapper = WrapperPojoCategoriesMapper.getInstance();
        initComponents();
        lblIdProduct.setVisible(false);

        loadTitlesAndData();
        initFilter();
        initPanelManagement();
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
        lblIdProduct = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        lblQuantity = new javax.swing.JLabel();
        lblCostOfSale = new javax.swing.JLabel();
        lblBarCode = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();

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

        lblProducto.setText("Producto");

        lblQuantity.setText("Cantidad en existencia");

        lblCostOfSale.setText("Costo de venta");

        lblBarCode.setText("Código de barras");

        lblPrice.setText("Precio");

        javax.swing.GroupLayout pnlManageProductLayout = new javax.swing.GroupLayout(pnlManageProduct);
        pnlManageProduct.setLayout(pnlManageProductLayout);
        pnlManageProductLayout.setHorizontalGroup(
            pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdProduct)
                    .addGroup(pnlManageProductLayout.createSequentialGroup()
                        .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblQuantity)
                            .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlManageProductLayout.createSequentialGroup()
                                    .addComponent(nmbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(44, 44, 44)
                                    .addComponent(lstReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblProducto)
                            .addComponent(btnSave))
                        .addGap(18, 18, 18)
                        .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblBarCode)
                            .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnCancel)
                                .addGroup(pnlManageProductLayout.createSequentialGroup()
                                    .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlManageProductLayout.createSequentialGroup()
                                            .addComponent(nmbCostOfSale, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18))
                                        .addGroup(pnlManageProductLayout.createSequentialGroup()
                                            .addComponent(lblCostOfSale)
                                            .addGap(39, 39, 39)))
                                    .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nmbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtBarCode))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlManageProductLayout.setVerticalGroup(
            pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlManageProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblProducto)
                        .addComponent(lblCostOfSale)))
                .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlManageProductLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblIdProduct))
                    .addGroup(pnlManageProductLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nmbCostOfSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nmbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblQuantity)
                            .addComponent(lblBarCode))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lstReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nmbQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlManageProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addGap(32, 32, 32))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            newData.setIdProduct(Long.parseLong(GUICommons.getTextFromLabel(lblIdProduct)));
            newData.setProduct(GUICommons.getTextFromJText(txtName));
            newData.setBarCode(GUICommons.getTextFromJText(txtBarCode));
            newData.setCostOfSale(GUICommons.getNumberFromJText(nmbCostOfSale));
            newData.setPrice(GUICommons.getNumberFromJText(nmbPrice));
            var reasonEnum = ReasonsEnum.PRODUCT_NOT_MODIFIED;
            var type = TypesEnum.UPDATE_PRODUCT;
            if (lstReason.isVisible()) {
                type = TypesEnum.ADJUST;
                newData.setQuantity(GUICommons.getNumberFromJText(nmbQuantity));
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
            newData.setQuantity(GUICommons.getNumberFromJText(nmbQuantity));
            productsController.updateProductInfo(
                productMapper.toInner(newData),
                ReasonsIntEnum.valueOf(reasonEnum.name()),
                userData.getIdUser(),
                TypesIntEnum.valueOf(type.name()));
            loadTitlesAndData();
            initPanelManagement();
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

    /** ajustar filtro de categorias */
    private void loadTitlesAndData() {
        try {
            final var productsData = productsMapper.toOuter(this.productsController.getAllProducts());
            final var categories = categoriesMapper.toOuter(this.categories.getAllCategories());
            if (userData.getRole().equals(RolesEnum.ROOT)) {
                final String[] titles = {"ID", "Codigo de barras", "Producto", "Cantidad en existencia", "Precio", "Costo de venta", "¿Por kg?", "Categoria"};
                GUICommons.loadTitleOnTable(tblProducts, titles, false);
                final var model = (DefaultTableModel) tblProducts.getModel();
                model.setRowCount(0);
                productsData.getProducts().forEach(p -> {
                    /** filtro para buscar nombre de categorias */
                    final var category = categories.getCategories().stream().filter(c -> c.getIdCategory() == p.getFkCategory()).findFirst().get();
                    final Object[] row = {
                        p.getIdProduct(),
                        p.getBarCode(),
                        p.getProduct(),
                        p.getQuantity(),
                        p.getPrice(),
                        p.getCostOfSale(),
                        p.isKg() ? "SI": "NO",
                        category.getCategory()
                    };
                    model.addRow(row);
                });
            }
            /** se actualiza cuando hay un cambio en algun producto */
            GUICommons.addDoubleClickOnTable(tblProducts, (Long id) -> {
                logger.log(id + "<- id");
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
                    GUICommons.setTextToLabel(lblIdProduct, productSelected.getIdProduct() + "");
                }
            });
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
        GUICommons.setTextToLabel(lblIdProduct, BloSalesV2Utils.EMPTY_STRING);
        GUICommons.setTextToField(txtName, BloSalesV2Utils.EMPTY_STRING);
        GUICommons.setTextToField(txtBarCode, BloSalesV2Utils.EMPTY_STRING);
        GUICommons.setTextToField(nmbCostOfSale, BloSalesV2Utils.EMPTY_STRING);
        GUICommons.setTextToField(nmbPrice, BloSalesV2Utils.EMPTY_STRING);
        GUICommons.setTextToField(nmbQuantity, BloSalesV2Utils.EMPTY_STRING);
        GUICommons.setTextToLabel(lblIdProduct, BloSalesV2Utils.EMPTY_STRING);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBarCode;
    private javax.swing.JLabel lblCostOfSale;
    private javax.swing.JLabel lblIdProduct;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblQuantity;
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
