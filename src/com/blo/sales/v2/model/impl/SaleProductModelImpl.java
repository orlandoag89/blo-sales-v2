package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntSaleProduct;
import com.blo.sales.v2.model.ISaleProductModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.mapper.SaleProductEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUILogger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SaleProductModelImpl implements ISaleProductModel {
    
    private static final GUILogger logger = GUILogger.getLogger(SaleProductModelImpl.class.getName());
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static SaleProductModelImpl instance;
    
    private static final SaleProductEntityMapper mapper = SaleProductEntityMapper.getInstance();
    
    private SaleProductModelImpl() { }
    
    public static SaleProductModelImpl getInstance() {
        if (instance == null) {
            instance = new SaleProductModelImpl();
        }
        return instance;
    }

    @Override
    public PojoIntSaleProduct addSaleProduct(PojoIntSaleProduct sale) throws BloSalesV2Exception {
        try {
            logger.log("guardando relacion venta producto");
            DBConnection.disableAutocommit();
            final var saleProduct = mapper.toInner(sale);
            // 2. Usar prepareStatement con RETURN_GENERATED_KEYS (Más estándar que prepareCall para INSERT)
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_SALE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, saleProduct.getFk_sale());
            ps.setLong(2, saleProduct.getFk_product());
            ps.setBigDecimal(3, saleProduct.getQunatity_sale());
            ps.setBigDecimal(4, saleProduct.getTotal_on_sale());
            ps.setBigDecimal(5, saleProduct.getProduct_total_on_sale());
            ps.setString(6, saleProduct.getTimestamp());
            final var rowsAffected = ps.executeUpdate();
            
            BloSalesV2Utils.validateRule(rowsAffected == 0, BloSalesV2Utils.SQL_ADD_EXCEPTION_CODE, BloSalesV2Utils.ERROR_SAVED_ON_DATA_BASE);
            
            final var rs = ps.getGeneratedKeys();
            if (rs.next()){
                saleProduct.setId_sale_product(rs.getLong(1));
                DBConnection.doCommit();
            }
            logger.log("relacion guardada " + saleProduct.toString());
            return mapper.toOuter(saleProduct);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
                throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
            }
        }
    }
    
}
