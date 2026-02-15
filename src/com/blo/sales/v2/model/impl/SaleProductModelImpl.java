package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntSaleProduct;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntSaleStock;
import com.blo.sales.v2.model.ISaleProductModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Columns;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.entities.SaleProductEntity;
import com.blo.sales.v2.model.entities.WrapperSaleStockEntity;
import com.blo.sales.v2.model.mapper.SaleProductEntityMapper;
import com.blo.sales.v2.model.mapper.WrapperSaleStockEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUILogger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SaleProductModelImpl implements ISaleProductModel {
    
    private static final GUILogger logger = GUILogger.getLogger(SaleProductModelImpl.class.getName());
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static SaleProductModelImpl instance;
    
    private static final SaleProductEntityMapper mapper = SaleProductEntityMapper.getInstance();
    
    private static final WrapperSaleStockEntityMapper saleStockEntityMapper = WrapperSaleStockEntityMapper.getInstance();
    
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

    @Override
    public PojoIntSaleProduct getRelationship(long fkSale, long fkProduct) throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_SALES_PRODUCT);
            ps.setLong(1, fkSale);
            ps.setLong(2, fkProduct);
            final var data = ps.executeQuery();
            SaleProductEntity out = null;
            while(data.next()) {
                out = new SaleProductEntity();
                out.setId_sale_product(data.getLong(BloSalesV2Columns.ID_SALE_PRODUCT));
                out.setFk_product(data.getLong(BloSalesV2Columns.FK_PRODUCT));
                out.setFk_sale(data.getLong(BloSalesV2Columns.FK_SALE));
                out.setProduct_total_on_sale(data.getBigDecimal(BloSalesV2Columns.PRODUCT_TOTAL_ON_SALE));
                out.setQunatity_sale(data.getBigDecimal(BloSalesV2Columns.QUANTITY_ON_SALE));
                out.setTimestamp(data.getString(BloSalesV2Columns.TIMESTAMP));
                out.setTotal_on_sale(data.getBigDecimal(BloSalesV2Columns.TOTAL_ON_SALE));
            }
            logger.log(String.format("datos encontrados %s", out));
            return mapper.toOuter(out);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public PojoIntSaleProduct updateRelationship(PojoIntSaleProduct data) throws BloSalesV2Exception {
        try {
            final var innerInfo = mapper.toInner(data);
            DBConnection.disableAutocommit();
            final var ps = conn.prepareStatement(BloSalesV2Queries.UPDATE_SALE_PRODUCT_RELATIONSHIP);
            ps.setBigDecimal(1, innerInfo.getQunatity_sale());
            ps.setBigDecimal(2, innerInfo.getTotal_on_sale());
            ps.setBigDecimal(3, innerInfo.getProduct_total_on_sale());
            ps.setString(4, innerInfo.getTimestamp());
            ps.setBoolean(5, innerInfo.is_live());
            ps.setLong(6, innerInfo.getId_sale_product());
            final var rowsAffected = ps.executeUpdate();
            
            BloSalesV2Utils.validateRule(rowsAffected == 0, BloSalesV2Utils.SQL_UPDATE_EXCEPTION_CODE, BloSalesV2Utils.ERROR_UPDATING_ON_DATA_BASE);
            
            DBConnection.doCommit();
            return mapper.toOuter(innerInfo);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
            }
        }
    }

    @Override
    public WrapperPojoIntSaleStock getSalesStockLiveByIdSale(long fkSale) throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_SALES_PRODUCT_BY_FK_SALE);
            ps.setLong(1, fkSale);
            final var data = ps.executeQuery();
            final var out = new WrapperSaleStockEntity();
            final var lst = new ArrayList<SaleProductEntity>();
            SaleProductEntity item = null;
            while(data.next()) {
                item = new SaleProductEntity();
                item.setId_sale_product(data.getLong(BloSalesV2Columns.ID_SALE_PRODUCT));
                item.setFk_product(data.getLong(BloSalesV2Columns.FK_PRODUCT));
                item.setFk_sale(data.getLong(BloSalesV2Columns.FK_SALE));
                item.setProduct_total_on_sale(data.getBigDecimal(BloSalesV2Columns.PRODUCT_TOTAL_ON_SALE));
                item.setQunatity_sale(data.getBigDecimal(BloSalesV2Columns.QUANTITY_ON_SALE));
                item.setTimestamp(data.getString(BloSalesV2Columns.TIMESTAMP));
                item.setTotal_on_sale(data.getBigDecimal(BloSalesV2Columns.TOTAL_ON_SALE));
                item.setIs_live(data.getBoolean(BloSalesV2Columns.IS_LIVE));
                lst.add(item);
            }
            logger.log(String.format("datos encontrados %s", lst.size()));
            out.setSalesStock(lst);
            return saleStockEntityMapper.toOuter(out);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        }
    }
    
}
