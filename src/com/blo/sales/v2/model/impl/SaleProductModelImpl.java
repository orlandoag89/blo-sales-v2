package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntSaleProduct;
import com.blo.sales.v2.model.ISaleProductModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.Queries;
import com.blo.sales.v2.model.mapper.SaleProductEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleProductModelImpl implements ISaleProductModel {
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static SaleProductModelImpl instance;
    
    private SaleProductEntityMapper mapper;
    
    private SaleProductModelImpl() {
        mapper = SaleProductEntityMapper.getInstance();
    }
    
    public static SaleProductModelImpl getInstance() {
        if (instance == null) {
            instance = new SaleProductModelImpl();
        }
        return instance;
    }

    @Override
    public PojoIntSaleProduct addSaleProduct(PojoIntSaleProduct sale) throws BloSalesV2Exception {
        try {
            DBConnection.disableAutocommit();
            final var saleProduct = mapper.toInner(sale);
            // 2. Usar prepareStatement con RETURN_GENERATED_KEYS (Más estándar que prepareCall para INSERT)
            final var ps = conn.prepareStatement(Queries.INSERT_SALE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, saleProduct.getFk_sale());
            ps.setLong(2, saleProduct.getFk_product());
            ps.setBigDecimal(3, saleProduct.getQunatity_sale());
            ps.setBigDecimal(4, saleProduct.getTotal_on_sale());
            ps.setString(5, saleProduct.getTimestamp());
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception("Error en guardado en la base de datos");
            }
            final var rs = ps.getGeneratedKeys();
            if (rs.next()){
                saleProduct.setId_sale_product(rs.getLong(1));
                DBConnection.doCommit();
            }
            return mapper.toOuter(saleProduct);
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
