package com.example.restorationmanagement.repositories.imp;

import com.example.restorationmanagement.dto.GlobalViewOfSalesData;
import com.example.restorationmanagement.repositories.GlobalViewOfSalesDataRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GlobalViewOfSalesDataRepositoryImp implements GlobalViewOfSalesDataRepository {
    private final Connection connection;

    public GlobalViewOfSalesDataRepositoryImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<GlobalViewOfSalesData> findListGlobalViewOfSalesData() {
        final var query = """
                SELECT
                    i.id AS ingredient_id,
                    i.name AS ingredient_name,
                    m.name AS menu_name,
                    SUM(mi.quantity * s.quantity) AS total_quantity_used,
                    u.name AS unit_name
                FROM
                    sale AS s
                        INNER JOIN menu AS m ON s.menu_id = m.id
                        INNER JOIN menuIngredient AS mi ON m.id = mi.menu_id
                        INNER JOIN ingredient AS i ON mi.ingredient_id = i.id
                        INNER JOIN unit AS u ON i.unit_id = u.id
                WHERE
                    s.saleDate >= '2024-05-01 08:00' AND s.saleDate <= '2024-05-01 10:10'
                  AND s.restaurant_id = 1
                GROUP BY
                    i.id, i.name, m.name, u.name
                ORDER BY
                    total_quantity_used DESC
                    LIMIT 3;
                """;
        final var globalViewOfSalesData = new ArrayList<GlobalViewOfSalesData>();
        try (final var stmt = connection.prepareStatement(query);
            final var rs = stmt.executeQuery()){
            while (rs.next()){
                globalViewOfSalesData.add(mapGlobalViewOfSalesData(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return globalViewOfSalesData;
    }

    private GlobalViewOfSalesData mapGlobalViewOfSalesData(ResultSet rs) throws SQLException {
        return new GlobalViewOfSalesData(
                rs.getInt("ingredient_id"),
                rs.getString("ingredient_name"),
                rs.getString("menu_name"),
                rs.getDouble("total_quantity_used"),
                rs.getString("unit_name")
        );
    }
}
