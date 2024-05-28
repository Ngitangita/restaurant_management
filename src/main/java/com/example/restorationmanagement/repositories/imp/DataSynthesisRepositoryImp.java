package com.example.restorationmanagement.repositories.imp;

import com.example.restorationmanagement.dto.DataSynthesis;
import com.example.restorationmanagement.repositories.DataSynthesisRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataSynthesisRepositoryImp implements DataSynthesisRepository {
    private final Connection connection;

    public DataSynthesisRepositoryImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<DataSynthesis> findDataSynthesis() {
        final var query = """
                SELECT
                    r.id AS restaurant_id,
                    r.location AS restaurant_location,
                    COALESCE(SUM(CASE WHEN m.id = 1 THEN s.quantity ELSE 0 END), 0) AS menu_1_quantity,
                    COALESCE(SUM(CASE WHEN m.id = 2 THEN s.quantity ELSE 0 END), 0) AS menu_2_quantity,
                    COALESCE(SUM(CASE WHEN m.id = 3 THEN s.quantity ELSE 0 END), 0) AS menu_3_quantity,
                    COALESCE(SUM(CASE WHEN m.id = 1 THEN s.price * s.quantity ELSE 0 END), 0) AS menu_1_revenue,
                    COALESCE(SUM(CASE WHEN m.id = 2 THEN s.price * s.quantity ELSE 0 END), 0) AS menu_2_revenue,
                    COALESCE(SUM(CASE WHEN m.id = 3 THEN s.price * s.quantity ELSE 0 END), 0) AS menu_3_revenue
                FROM
                    restaurant AS r
                        LEFT JOIN sale AS s ON r.id = s.restaurant_id
                        LEFT JOIN menu AS m ON s.menu_id = m.id
                WHERE
                    s.saleDate >= '2024-05-01 08:00' AND s.saleDate <= '2024-05-01 10:10'
                GROUP BY
                    r.id, r.location
                ORDER BY
                    r.id;
                """;
        final var dataSynthesis = new ArrayList<DataSynthesis>();
        try (final var stmt = connection.prepareStatement(query);
            final var rs = stmt.executeQuery()){
            while (rs.next()){
                dataSynthesis.add(mapDataSynthesis(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataSynthesis;
    }

    private DataSynthesis mapDataSynthesis(ResultSet rs) throws SQLException {
        return new DataSynthesis(
                rs.getInt("restaurant_id"),
                rs.getString("restaurant_location"),
                rs.getDouble("menu_1_quantity"),
                rs.getDouble("menu_2_quantity"),
                rs.getDouble("menu_3_quantity"),
                rs.getDouble("menu_1_revenue"),
                rs.getDouble("menu_2_revenue"),
                rs.getDouble("menu_3_revenue")
                );
    }
}
