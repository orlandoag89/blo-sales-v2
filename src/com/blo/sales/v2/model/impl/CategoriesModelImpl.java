package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntCategory;
import com.blo.sales.v2.controller.pojos.WrapperIntPojoCategories;
import com.blo.sales.v2.model.ICategoriesModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.Queries;
import com.blo.sales.v2.model.constants.QueriesErrors;
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

public class CategoriesModelImpl implements ICategoriesModel {

    private static final Connection conn = DBConnection.getConnection();

    private CategoryEntityMapper categoryMapper;
    private WrapperCategoriesEntityMapper wrapperCategoriesEntityMapper;

    public CategoriesModelImpl() {
        categoryMapper = new CategoryEntityMapper();
        wrapperCategoriesEntityMapper = new WrapperCategoriesEntityMapper();
    }

    @Override
    public PojoIntCategory registerCategory(PojoIntCategory category) throws BloSalesV2Exception {
        try {
            final var data = categoryMapper.toInner(category);
            // 1. Desactivar el AutoCommit para iniciar la transacción
            conn.setAutoCommit(false);
            // 2. Usar prepareStatement con RETURN_GENERATED_KEYS (Más estándar que prepareCall para INSERT)
            final var ps = conn.prepareStatement(Queries.INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, data.getCategory());
            ps.setString(2, data.getDescription());
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                final var rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    data.setId_category(rs.getInt(1));
                }
            }
            // 3. Si todo salió bien, confirmamos los cambios en la DB
            conn.commit();
            return categoryMapper.toOuter(data);
        } catch (SQLException e) {
            throw new BloSalesV2Exception(e.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                throw new BloSalesV2Exception(e.getMessage());
            }
        }
    }

    @Override
    public WrapperIntPojoCategories getAllCategories() throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(Queries.SELECT_ALL_DATA_FROM_CATEGORIES);
            final var data = ps.executeQuery();
            final var categories = new ArrayList<CategoryEntity>();
            final var wrapper = new WrapperCategoriesEntity();
            while(data.next()) {
                final var category = new CategoryEntity();
                category.setId_category(data.getInt("id_category"));
                category.setCategory(data.getString("category"));
                category.setDescription(data.getString("description"));
                categories.add(category);
            }
            wrapper.setCategories(categories);
            return wrapperCategoriesEntityMapper.toOuter(wrapper);
        } catch (SQLException ex) {
            throw new BloSalesV2Exception(ex.getMessage());
        }
    }

    @Override
    public PojoIntCategory updateCategory(int id, PojoIntCategory newData) throws BloSalesV2Exception {
        try {
            conn.setAutoCommit(false);
            final var categoryFound = getCategoryById(id);
            categoryFound.setCategory(newData.getCategory());
            categoryFound.setDescription(newData.getDescription());
            final var ps = conn.prepareStatement(Queries.UPDATE_CATEGORY);
            ps.setString(1, categoryFound.getCategory());
            ps.setString(2, categoryFound.getDescription());
            ps.setInt(3, id);
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception("No se ejecuto correctamente la actualizacion");
            }
            conn.commit();
            return categoryMapper.toOuter(categoryFound);
        } catch (SQLException e) {
            throw new BloSalesV2Exception(e.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                throw new BloSalesV2Exception(e.getMessage());
            }
        }
    }

    private CategoryEntity getCategoryById(int id) throws BloSalesV2Exception, SQLException {
        final var ps = conn.prepareStatement(Queries.SELECT_CATEGORY);
        ps.setInt(1, id);
        final var rs = ps.executeQuery();
        BloSalesV2Utils.validateRule(!rs.next(), QueriesErrors.CATEGORY_ERROR);
        final var category = new CategoryEntity();
        category.setId_category(rs.getInt(1));
        category.setCategory(rs.getString(2));
        category.setDescription(rs.getString(3));
        return category;
    }

}
