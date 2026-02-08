package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntStockPricesHistory;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntStockPriceHistory;
import com.blo.sales.v2.model.IStockPricesHistoryModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Columns;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.entities.ProductEntity;
import com.blo.sales.v2.model.entities.StockPriceHistoryEntity;
import com.blo.sales.v2.model.entities.WrapperProductsEntity;
import com.blo.sales.v2.model.entities.WrapperStockPricesHistoryEntity;
import com.blo.sales.v2.model.mapper.StockPricesHistoryEntityMapper;
import com.blo.sales.v2.model.mapper.WrapperStockPricesHistoryEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUILogger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StockPricesHistoryModelImpl implements IStockPricesHistoryModel {
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static final GUILogger logger = GUILogger.getLogger(StockPricesHistoryModelImpl.class.getName());
    
    private static final StockPricesHistoryEntityMapper mapper = StockPricesHistoryEntityMapper.getInstance();
    
    private static final WrapperStockPricesHistoryEntityMapper wrapperMapper = WrapperStockPricesHistoryEntityMapper.getInstance();
    
    private static StockPricesHistoryModelImpl instance;
    
    private StockPricesHistoryModelImpl() { }
    
    public static StockPricesHistoryModelImpl getInstance() {
        if(instance == null) {
            instance = new StockPricesHistoryModelImpl();
        }
        return instance;
    }

    @Override
    public PojoIntStockPricesHistory addPriceOnHistory(PojoIntStockPricesHistory item) throws BloSalesV2Exception {
        try {
            final var entity = mapper.toInner(item);
            DBConnection.disableAutocommit();
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_PRICE_HISTORY_RELATIONSHIP, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, entity.getFk_product());
            ps.setLong(2, entity.getFk_price_history());
            ps.setString(3, entity.getTimesTamp());
            final var rowsAffected = ps.executeUpdate();
            
            BloSalesV2Utils.validateRule(rowsAffected == 0, BloSalesV2Utils.SQL_ADD_EXCEPTION_CODE, BloSalesV2Utils.ERROR_SAVED_ON_DATA_BASE);
            
            final var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                entity.setId_stock_prices_history(rs.getLong(1));
            }
            DBConnection.doCommit();
            return mapper.toOuter(entity);
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
    public WrapperPojoIntStockPriceHistory getPriceFromProduct(long idProduct) throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(BloSalesV2Queries.RETRIEVE_STOCK_PRICE_HISTORY);
            ps.setLong(1, idProduct);
            final var rs = ps.executeQuery();
            
            final var wrapperHistory = new WrapperStockPricesHistoryEntity();
            final var historyStockPrice = new ArrayList<StockPriceHistoryEntity>();
            StockPriceHistoryEntity item = null;
            while(rs.next()) {
                item = new StockPriceHistoryEntity();
                item.setId_stock_price_history(rs.getLong(BloSalesV2Columns.ID_STOCK_PRICE_HISTORY));
                item.setCostOfSale(rs.getBigDecimal(BloSalesV2Columns.COST_OF_SALE));
                item.setPrice(rs.getBigDecimal(BloSalesV2Columns.PRICE));
                item.setProduct(rs.getString(BloSalesV2Columns.PRODUCT));
                item.setTimestamp(rs.getString(BloSalesV2Columns.TIMESTAMP));
                historyStockPrice.add(item);
                logger.log("item info " + item.toString());
            }
            wrapperHistory.setHistory(historyStockPrice);
            return wrapperMapper.toOuter(wrapperHistory);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        }
    }
    
}
