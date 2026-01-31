package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntCategory;
import com.blo.sales.v2.controller.pojos.WrapperIntPojoCategories;
import com.blo.sales.v2.model.ICategoriesModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Columns;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.entities.CategoryEntity;
import com.blo.sales.v2.model.entities.WrapperCategoriesEntity;
import com.blo.sales.v2.model.mapper.WrapperCategoriesEntityMapper;
import com.blo.sales.v2.model.mapper.CategoryEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriesModelImpl implements ICategoriesModel {

    private static final Connection conn = DBConnection.getConnection();

    private static final CategoryEntityMapper categoryMapper = CategoryEntityMapper.getInstance();
   
    private static final WrapperCategoriesEntityMapper wrapperCategoriesEntityMapper = WrapperCategoriesEntityMapper.getInstance();

    public static CategoriesModelImpl instance;
    
    private CategoriesModelImpl() { }
    
    public static CategoriesModelImpl getInstance() {
        if (instance == null) {
            instance = new CategoriesModelImpl();
        }
        return instance;
    }

    @Override
    public PojoIntCategory registerCategory(PojoIntCategory category) throws BloSalesV2Exception {
        try {
            final var data = categoryMapper.toInner(category);
            // 1. Desactivar el AutoCommit para iniciar la transacción
            DBConnection.disableAutocommit();
            // 2. Usar prepareStatement con RETURN_GENERATED_KEYS (Más estándar que prepareCall para INSERT)
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, data.getCategory());
            ps.setString(2, data.getDescription());
            final var rowsAffected = ps.executeUpdate();
            
            BloSalesV2Utils.validateRule(rowsAffected == 0, BloSalesV2Utils.SQL_ADD_EXCEPTION_CODE, BloSalesV2Utils.ERROR_SAVED_ON_DATA_BASE);
            
            final var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                data.setId_category(rs.getInt(1));
            }
            // 3. Si todo salió bien, confirmamos los cambios en la DB
            DBConnection.doCommit();
            return categoryMapper.toOuter(data);
        } catch (SQLException e) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException e) {
                Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, e);
                throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
            }
        }
    }

    @Override
    public WrapperIntPojoCategories getAllCategories() throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_ALL_DATA_FROM_CATEGORIES);
            final var data = ps.executeQuery();
            final var categories = new ArrayList<CategoryEntity>();
            final var wrapper = new WrapperCategoriesEntity();
            while(data.next()) {
                final var category = new CategoryEntity();
                category.setId_category(data.getInt(BloSalesV2Columns.ID_CATEGORY));
                category.setCategory(data.getString(BloSalesV2Columns.CATEGORY));
                category.setDescription(data.getString(BloSalesV2Columns.DESCRIPTION));
                categories.add(category);
            }
            wrapper.setCategories(categories);
            return wrapperCategoriesEntityMapper.toOuter(wrapper);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public PojoIntCategory updateCategory(long id, PojoIntCategory newData) throws BloSalesV2Exception {
        try {
            DBConnection.disableAutocommit();
            final var category = getCategoryById(id);
            final var categoryFound = categoryMapper.toInner(category);
            categoryFound.setCategory(newData.getCategory());
            categoryFound.setDescription(newData.getDescription());
            final var ps = conn.prepareStatement(BloSalesV2Queries.UPDATE_CATEGORY);
            ps.setString(1, categoryFound.getCategory());
            ps.setString(2, categoryFound.getDescription());
            ps.setLong(3, id);
            final var rowsAffected = ps.executeUpdate();
            
            BloSalesV2Utils.validateRule(rowsAffected == 0, BloSalesV2Utils.SQL_UPDATE_EXCEPTION_CODE, BloSalesV2Utils.ERROR_UPDATING_ON_DATA_BASE);
            
            DBConnection.doCommit();
            return categoryMapper.toOuter(categoryFound);
        } catch (SQLException e) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException e) {
                Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, e);
                throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
            }
        }
    }

    @Override
    public PojoIntCategory getCategoryById(long id) throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_CATEGORY);
            ps.setLong(1, id);
            final var rs = ps.executeQuery();
            BloSalesV2Utils.validateRule(!rs.next(), BloSalesV2Utils.CODE_CATEGORY_NOT_FOUND, BloSalesV2Utils.ERROR_CATEGORY_NOT_FOUND);
            final var category = new CategoryEntity();
            category.setId_category(rs.getInt(1));
            category.setCategory(rs.getString(2));
            category.setDescription(rs.getString(3));
            return categoryMapper.toOuter(category);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        }
    }

}
