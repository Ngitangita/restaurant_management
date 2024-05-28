package com.example.restorationmanagement.repositories.imp;

import com.example.restorationmanagement.dto.StockManagement;
import com.example.restorationmanagement.enumes.MovementType;
import com.example.restorationmanagement.repositories.StockManagementRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StockManagementRepositoryImp implements StockManagementRepository {
    private final Connection connection;

    public StockManagementRepositoryImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<StockManagement> findStockManagement() {
        final var query = """
                SELECT
                    s.movement_date,
                    i.name AS ingredient,
                    s.movement_type,
                    s.quantity,
                    u.name AS unit
                FROM
                    stock AS s
                        INNER JOIN ingredient AS i ON s.ingredient_id = i.id
                        INNER JOIN unit AS u ON i.unit_id = u.id
                WHERE
                    s.movement_date >= '2024-05-01 08:00:00' AND s.movement_date <= '2024-05-02 10:10:00'
                ORDER BY
                    s.movement_date;
                """;
        final var stockManagements = new ArrayList<StockManagement>();
        try (final var stmt = connection.prepareStatement(query);
            final var rs = stmt.executeQuery()){
            while (rs.next()){
                stockManagements.add(mapStockManagement(rs));
            }
            return stockManagements;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private StockManagement mapStockManagement(ResultSet rs) throws SQLException {
        return new StockManagement(
                rs.getString("ingredient"),
                MovementType.valueOf(rs.getString("movement_type")),
                rs.getDouble("quantity"),
                rs.getTimestamp("movement_date").toInstant(),
                rs.getString("unit")
        );
    }
}
