package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntProduct;
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

public class ProductsModelImpl implements IProductsModel {
    
    private static final Connection conn = DBConnection.getConnection();
    
    private ProductEntityMapper mapper;
    
    public ProductsModelImpl() {
        mapper = new ProductEntityMapper();
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
    
}
