package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntCategory;
import com.blo.sales.v2.model.ICategoriesModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.Queries;
import com.blo.sales.v2.model.mapper.CategoryEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoriesModelImpl implements ICategoriesModel {

    private static final Connection conn = DBConnection.getConnection();

    private CategoryEntityMapper categoryMapper;

    public CategoriesModelImpl() {
        categoryMapper = new CategoryEntityMapper();
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
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                throw new BloSalesV2Exception(e.getMessage());
            }
        }
     /* final var data = categoryMapper.toInner(category);
    
    try {
        // 1. Desactivar el AutoCommit para iniciar la transacción
        conn.setAutoCommit(false);

        // 2. Usar prepareStatement con RETURN_GENERATED_KEYS (Más estándar que prepareCall para INSERT)
        try (var ps = conn.prepareStatement(Queries.INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, data.getCategory());
            ps.setString(2, data.getDescription());

            final var rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                try (var rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        data.setId_category(rs.getInt(1));
                    }
                }
            }

            // 3. Si todo salió bien, confirmamos los cambios en la DB
            conn.commit();
            
        } catch (SQLException e) {
            // 4. Si hubo un error en el PS, deshacemos cualquier cambio
            conn.rollback();
            throw e; // Relanzamos el error para que la UI sepa que falló
        }
    }   catch (SQLException ex) {
            Logger.getLogger(CategoriesModelImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
          try {
              // 5. IMPORTANTE: Devolver la conexión a su estado normal
              conn.setAutoCommit(true);
          } catch (SQLException ex) {
              Logger.getLogger(CategoriesModelImpl.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    return categoryMapper.toOuter(data);*/
    }

}
