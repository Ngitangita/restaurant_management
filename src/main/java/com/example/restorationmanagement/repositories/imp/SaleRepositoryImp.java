package com.example.restorationmanagement.repositories.imp;

import com.example.restorationmanagement.entities.Menu;
import com.example.restorationmanagement.entities.Restaurant;
import com.example.restorationmanagement.entities.Sale;
import com.example.restorationmanagement.repositories.SaleRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SaleRepositoryImp implements SaleRepository {
    private final Connection connection;

    public SaleRepositoryImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Sale create(Sale toCreate) {
        final var query = "INSERT INTO sale (restaurant_id, menu_id, saleDate, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try (final var stmt = connection.prepareStatement(query)){
            setSaleParameters(stmt, toCreate);
            final var rows = stmt.executeUpdate();
            if (rows > 0){
                try (final var generatedKeys = stmt.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        final var id = generatedKeys.getInt(1);
                        return findById(id).orElse(null);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void setSaleParameters(PreparedStatement stmt, Sale sale) throws SQLException {
        stmt.setInt(1, sale.getRestaurant().getId());
        stmt.setInt(2, sale.getMenu().getId());
        stmt.setTimestamp(3, Timestamp.from(sale.getSaleDate()));
        stmt.setInt(4, sale.getQuantity());
        stmt.setDouble(5, sale.getPrice());
    }

    @Override
    public List<Sale> findAll() {
        final var query = "SELECT * FROM sale";
        final var sales = new ArrayList<Sale>();
        try (final var stmt = connection.prepareStatement(query);
             final var rs = stmt.executeQuery()){
            while (rs.next()){
                sales.add(mapResultSetToSale(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sales;
    }

    private Sale mapResultSetToSale(ResultSet rs) throws SQLException {
        return new Sale()
                .setId(rs.getInt("id"))
                .setRestaurant(Restaurant.builder().id(rs.getInt("restaurant_id")).build())
                .setMenu(Menu.builder().id(rs.getInt("menu_id")).build())
                .setSaleDate(rs.getTimestamp("saleDate").toInstant())
                .setQuantity(rs.getInt("quantity"))
                .setPrice(rs.getDouble("price"));
    }

    @Override
    public Optional<Sale> findById(Integer id) {
        final var query = "SELECT * FROM sale WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()){
                if (rs.next()){
                    return Optional.of(mapResultSetToSale(rs));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Sale update(Sale toUpdate) {
        final var query =  "UPDATE sale SET restaurant_id = ?, menu_id = ?, saleDate = ?, quantity = ?, price = ? WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)){
            setSaleParameters(stmt, toUpdate);
            stmt.setInt(6, toUpdate.getId());
            final var rows = stmt.executeUpdate();
            if (rows > 0){
                return toUpdate;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Optional<Sale> delete(Integer id) {
        final var foundSale = this.findById(id);
        final var query = "DELETE FROM sale WHERE id = ?";
        try (var stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            var rows = stmt.executeUpdate();
            if (rows > 0){
                return foundSale;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
