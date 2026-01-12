package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntProduct;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntProducts;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.Queries;
import com.blo.sales.v2.model.mapper.ProductEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.blo.sales.v2.model.IProductsModel;
import com.blo.sales.v2.model.entities.ProductEntity;
import com.blo.sales.v2.model.entities.WrapperProductsEntity;
import com.blo.sales.v2.model.mapper.WrapperProductsEntityMapper;
import java.util.ArrayList;

public class ProductsModelImpl implements IProductsModel {
    
    private static final Connection conn = DBConnection.getConnection();
    
    private ProductEntityMapper mapper;
    
    private WrapperProductsEntityMapper wrapperMapper;
    
    private static ProductsModelImpl instance;
    
    private ProductsModelImpl() {
        mapper = ProductEntityMapper.getInstance();
        wrapperMapper = WrapperProductsEntityMapper.getInstance();
    }
    
    public static ProductsModelImpl getInstance() {
        if (instance == null) {
            instance = new ProductsModelImpl();
        }
        return instance;
    }

    @Override
    public PojoIntProduct registerProduct(PojoIntProduct product) throws BloSalesV2Exception {
        try {
            DBConnection.disableAutocommit();
            final var innerProduct = mapper.toInner(product);
            // 2. Usar prepareStatement con RETURN_GENERATED_KEYS (Más estándar que prepareCall para INSERT)
            final var ps = conn.prepareStatement(Queries.INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, innerProduct.getProduct());
            ps.setBigDecimal(2, innerProduct.getQuantity());
            ps.setBigDecimal(3, product.getCostOfSale());
            ps.setBigDecimal(4, product.getPrice());
            ps.setString(5, product.getTimestamp());
            ps.setBoolean(6, product.isKg());
            ps.setString(7, product.getBarCode());
            ps.setInt(8, product.getFkCategory());
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                final var rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    innerProduct.setId_product(rs.getInt(1));
                }
            }
            DBConnection.doCommit();
            return mapper.toOuter(innerProduct);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(ex.getMessage());
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException ex) {
                Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
                throw new BloSalesV2Exception(ex.getMessage());
            }
        }
    }

    @Override
    public WrapperPojoIntProducts getAllProducts() throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(Queries.SELECT_ALL_PRODUCTS);
            final var rs = ps.executeQuery();
            final var productsInn = new WrapperProductsEntity();
            final var innerProducts = new ArrayList<ProductEntity>();
            while(rs.next()) {
                final var p = new ProductEntity();
                p.setBar_code(rs.getString("bar_code"));
                p.setCost_of_sale(rs.getBigDecimal("cost_of_sale"));
                p.setFk_category(rs.getInt("fk_category"));
                p.setId_product(rs.getInt("id_product"));
                p.setKg(rs.getBoolean("is_kg"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setQuantity(rs.getBigDecimal("quantity"));
                p.setTimestamp(rs.getString("timestamp"));
                p.setProduct(rs.getString("product"));
                innerProducts.add(p);
            }
            productsInn.setProducts(innerProducts);
            return wrapperMapper.toOuter(productsInn);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(ex.getMessage());
        }
    }
    
}
