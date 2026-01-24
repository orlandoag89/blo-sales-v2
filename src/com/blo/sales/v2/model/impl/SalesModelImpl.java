package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntSale;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntSales;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntSalesAndStock;
import com.blo.sales.v2.controller.pojos.enums.SalesStatusIntEnum;
import com.blo.sales.v2.model.ISalesModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Columns;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.entities.SaleAndProductEntity;
import com.blo.sales.v2.model.entities.SaleEntity;
import com.blo.sales.v2.model.entities.WrapperSalesAndStockEntity;
import com.blo.sales.v2.model.entities.WrapperSalesEntity;
import com.blo.sales.v2.model.entities.enums.SaleStatusEntityEnum;
import com.blo.sales.v2.model.mapper.SaleEntityMapper;
import com.blo.sales.v2.model.mapper.WrapperSalesAndStockEntityMapper;
import com.blo.sales.v2.model.mapper.WrapperSalesEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUILogger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SalesModelImpl implements ISalesModel {
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static final GUILogger logger = GUILogger.getLogger(SalesModelImpl.class.getName());
    
    private static final SaleEntityMapper saleMapper = SaleEntityMapper.getInstance();
    
    private static final WrapperSalesAndStockEntityMapper salesAndStockMapper = WrapperSalesAndStockEntityMapper.getInstance();
    
    private static final WrapperSalesEntityMapper salesEntityMapper = WrapperSalesEntityMapper.getInstance();
            
    private static SalesModelImpl instance;
    
    private SalesModelImpl() { }
    
    public static SalesModelImpl getInstance() {
        if (instance == null) {
            instance = new SalesModelImpl();
        }
        return instance;
    }

    @Override
    public PojoIntSale registerSale(PojoIntSale sale) throws BloSalesV2Exception {
        try {
            logger.log("se comienza a registrar venta");
            final var innerSale = saleMapper.toInner(sale);
            DBConnection.disableAutocommit();
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_SALE, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, innerSale.getTotal());
            ps.setString(2, innerSale.getSale_status().name());
            ps.setString(3, innerSale.getTimestamp());
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception(BloSalesV2Utils.ERROR_SAVED_ON_DATA_BASE);
            }
            final var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                innerSale.setId_sale(rs.getLong(1));
            }
            DBConnection.doCommit();
            logger.log("venta registrada " + innerSale.toString());
            return saleMapper.toOuter(innerSale);
        } catch (SQLException ex) {
            throw new BloSalesV2Exception(ex.getMessage());
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException ex) {
                throw new BloSalesV2Exception(ex.getMessage());
            }
        }
        
    }

    @Override
    public WrapperPojoIntSalesAndStock retrieveAllSalesDetail() throws BloSalesV2Exception {
         try {
            logger.log("recuperando relacion ventas y productos");
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_SALES_DETAIL);
            final var data = ps.executeQuery();
            final var wrapper = new WrapperSalesAndStockEntity();
            SaleAndProductEntity saleDetail = null;
            final var details = new ArrayList<SaleAndProductEntity>();
            while(data.next()) {
                saleDetail = new SaleAndProductEntity();
                saleDetail.setId_product(data.getLong(BloSalesV2Columns.ID_PRODUCT));
                saleDetail.setId_sale(data.getLong(BloSalesV2Columns.ID_SALE));
                saleDetail.setProduct(data.getString(BloSalesV2Columns.PRODUCT));
                saleDetail.setQuantity_on_sale(data.getBigDecimal(BloSalesV2Columns.QUANTITY_ON_SALE));
                saleDetail.setTotal_on_sale(data.getBigDecimal(BloSalesV2Columns.TOTAL_ON_SALE));
                saleDetail.setTimestamp(data.getString(BloSalesV2Columns.TIMESTAMP));
                details.add(saleDetail);
            }
            wrapper.setSalesDetail(details);
            logger.log("registros encontrados " + details.size());
            return salesAndStockMapper.toOuter(wrapper);
        } catch (SQLException ex) {
            throw new BloSalesV2Exception(ex.getMessage());
        }
    }

    @Override
    public WrapperPojoIntSalesAndStock retrieveSalesByStatus(SalesStatusIntEnum saleStatus) throws BloSalesV2Exception {
        try {
            logger.log("recuperando relacion ventas y productos");
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_SALE_CLOSED);
            ps.setString(1, saleStatus.name());
            final var data = ps.executeQuery();
            final var wrapper = new WrapperSalesAndStockEntity();
            SaleAndProductEntity saleDetail = null;
            final var details = new ArrayList<SaleAndProductEntity>();
            while(data.next()) {
                saleDetail = new SaleAndProductEntity();
                saleDetail.setId_product(data.getLong(BloSalesV2Columns.ID_PRODUCT));
                saleDetail.setId_sale(data.getLong(BloSalesV2Columns.ID_SALE));
                saleDetail.setProduct(data.getString(BloSalesV2Columns.PRODUCT));
                saleDetail.setQuantity_on_sale(data.getBigDecimal(BloSalesV2Columns.QUANTITY_ON_SALE));
                saleDetail.setTotal_on_sale(data.getBigDecimal(BloSalesV2Columns.TOTAL_ON_SALE));
                saleDetail.setTimestamp(data.getString(BloSalesV2Columns.TIMESTAMP));
                details.add(saleDetail);
            }
            wrapper.setSalesDetail(details);
            logger.log("registros encontrados " + details.size());
            return salesAndStockMapper.toOuter(wrapper);
        } catch (SQLException ex) {
            throw new BloSalesV2Exception(ex.getMessage());
        }
    }

    @Override
    public WrapperPojoIntSales retrieveSalesDataByStatus(SalesStatusIntEnum saleStatus) throws BloSalesV2Exception {
        try {
            logger.log("recuperando ventas " + saleStatus.name());
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_SALE_BY_STATUS);
            ps.setString(1, saleStatus.name());
            final var data = ps.executeQuery();
            final var wrapper = new WrapperSalesEntity();
            SaleEntity saleDetail = null;
            final var details = new ArrayList<SaleEntity>();
            while(data.next()) {
                saleDetail = new SaleEntity();
                saleDetail.setId_sale(data.getLong(BloSalesV2Columns.ID_SALE));
                saleDetail.setTotal(data.getBigDecimal(BloSalesV2Columns.TOTAL));
                saleDetail.setTimestamp(data.getString(BloSalesV2Columns.TIMESTAMP));
                saleDetail.setSales_status(SaleStatusEntityEnum.valueOf(data.getString(BloSalesV2Columns.SALE_STATUS)));
                details.add(saleDetail);
            }
            wrapper.setSales(details);
            logger.log("registros encontrados " + details.size());
            return salesEntityMapper.toOuter(wrapper);
        } catch (SQLException ex) {
            throw new BloSalesV2Exception(ex.getMessage());
        }
    }

    @Override
    public boolean setCashboxSale(long idSale) throws BloSalesV2Exception {
        try {
            logger.log("enviando a cashbox la venta " + idSale);
            DBConnection.disableAutocommit();
            final var ps = conn.prepareStatement(BloSalesV2Queries.SET_ON_CASHBOX);
            ps.setLong(1, idSale);
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception(BloSalesV2Utils.ERROR_UPDATING_ON_DATA_BASE);
            }
            DBConnection.doCommit();
            return true;
        } catch (SQLException ex) {
            throw new BloSalesV2Exception(ex.getMessage());
        }
    }
    
}
