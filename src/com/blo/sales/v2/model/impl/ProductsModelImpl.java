package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntProduct;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntProducts;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.mapper.ProductEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.blo.sales.v2.model.IProductsModel;
import com.blo.sales.v2.model.constants.BloSalesV2Columns;
import com.blo.sales.v2.model.entities.ProductEntity;
import com.blo.sales.v2.model.entities.WrapperProductsEntity;
import com.blo.sales.v2.model.mapper.WrapperProductsEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Utils;
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
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, innerProduct.getProduct());
            ps.setBigDecimal(2, innerProduct.getQuantity());
            ps.setBigDecimal(3, product.getCostOfSale());
            ps.setBigDecimal(4, product.getPrice());
            ps.setString(5, product.getTimestamp());
            ps.setBoolean(6, product.isKg());
            ps.setString(7, product.getBarCode());
            ps.setLong(8, product.getFkCategory());
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
    public WrapperPojoIntProducts getAllProducts() throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_ALL_PRODUCTS);
            final var rs = ps.executeQuery();
            final var productsInn = new WrapperProductsEntity();
            final var innerProducts = new ArrayList<ProductEntity>();
            while(rs.next()) {
                final var p = new ProductEntity();
                p.setBar_code(rs.getString(BloSalesV2Columns.BAR_CODE));
                p.setCost_of_sale(rs.getBigDecimal(BloSalesV2Columns.COST_OF_SALE));
                p.setFk_category(rs.getInt(BloSalesV2Columns.FK_CATEGORY));
                p.setId_product(rs.getInt(BloSalesV2Columns.ID_PRODUCT));
                p.setKg(rs.getBoolean(BloSalesV2Columns.IS_KG));
                p.setPrice(rs.getBigDecimal(BloSalesV2Columns.PRICE));
                p.setQuantity(rs.getBigDecimal(BloSalesV2Columns.QUANTITY));
                p.setTimestamp(rs.getString(BloSalesV2Columns.TIMESTAMP));
                p.setProduct(rs.getString(BloSalesV2Columns.PRODUCT));
                innerProducts.add(p);
            }
            productsInn.setProducts(innerProducts);
            return wrapperMapper.toOuter(productsInn);
        } catch (SQLException ex) {
            throw new BloSalesV2Exception(ex.getMessage());
        }
    }

    @Override
    public PojoIntProduct updateProductInfo(PojoIntProduct product) throws BloSalesV2Exception {
        try {
            final var innerProduct = mapper.toInner(product);
            DBConnection.disableAutocommit();
            final var ps = conn.prepareStatement(BloSalesV2Queries.UPDATE_PRODUCT);
            ps.setString(1, innerProduct.getProduct());
            ps.setBigDecimal(2, innerProduct.getQuantity());
            ps.setBigDecimal(3, innerProduct.getCost_of_sale());
            ps.setString(4, innerProduct.getTimestamp());
            ps.setString(5, innerProduct.getBar_code());
            ps.setBigDecimal(6, innerProduct.getPrice());
            ps.setLong(7, innerProduct.getId_product());
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception(BloSalesV2Utils.ERROR_UPDATING_ON_DATA_BASE);
            }
            DBConnection.doCommit();
            return mapper.toOuter(innerProduct);
        } catch (SQLException e) {
            throw new BloSalesV2Exception(e.getMessage());
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException e) {
                throw new BloSalesV2Exception(e.getMessage());
            }
        }
    }

    @Override
    public PojoIntProduct getProductById(long idProduct) throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_ONE_PRODUCT);
            ps.setLong(1, idProduct);
            final var rs = ps.executeQuery();
            BloSalesV2Utils.validateRule(!rs.next(), BloSalesV2Utils.ERROR_PRODUCT_NOT_FOUND);
            final var p = new ProductEntity();
            p.setBar_code(rs.getString(BloSalesV2Columns.BAR_CODE));
            p.setCost_of_sale(rs.getBigDecimal(BloSalesV2Columns.COST_OF_SALE));
            p.setFk_category(rs.getInt(BloSalesV2Columns.FK_CATEGORY));
            p.setId_product(rs.getInt(BloSalesV2Columns.ID_PRODUCT));
            p.setKg(rs.getBoolean(BloSalesV2Columns.IS_KG));
            p.setPrice(rs.getBigDecimal(BloSalesV2Columns.PRICE));
            p.setQuantity(rs.getBigDecimal(BloSalesV2Columns.QUANTITY));
            p.setTimestamp(rs.getString(BloSalesV2Columns.TIMESTAMP));
            p.setProduct(rs.getString(BloSalesV2Columns.PRODUCT));
            return mapper.toOuter(p);
        } catch (SQLException ex) {
            throw new BloSalesV2Exception(ex.getMessage());
        }
    }
    
}
