package com.example.restorationmanagement.repositories.imp;

import com.example.restorationmanagement.dto.request.QuantityIngredientRequest;
import com.example.restorationmanagement.entities.Ingredient;
import com.example.restorationmanagement.entities.Restaurant;
import com.example.restorationmanagement.entities.Stock;
import com.example.restorationmanagement.enumes.MovementType;
import com.example.restorationmanagement.repositories.StockRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StockRepositoryImp implements StockRepository {
    private final Connection connection;

    public StockRepositoryImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Stock create(Stock toCreate) {
        final var query = "INSERT INTO stock (restaurant_id, ingredient_id, movement_type, quantity, movement_date) VALUES (?, ?, ?, ?, ?)";
        try (final var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            setStockParameters(stmt, toCreate);
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

    @Override
    public List<Stock> findAll() {
        final var query = "SELECT * FROM stock";
        final var stocks = new ArrayList<Stock>();
        try (final var stmt = connection.prepareStatement(query);
             final var rs = stmt.executeQuery()){
            while (rs.next()){
                stocks.add(mapResutlSetToStock(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stocks;
    }

    @Override
    public Optional<Stock> findById(Integer id) {
        final var query = "SELECT * FROM stock WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()){
                if (rs.next()){
                    return Optional.of(mapResutlSetToStock(rs));
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
    public Stock update(Stock toUpdate) {
        final var query = "UPDATE stock SET restaurant_id = ?, ingredient_id = ?, movement_type = ?, quantity = ?, movement_date = ? WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)){
            setStockParameters(stmt, toUpdate);
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
    public Optional<Stock> delete(Integer id) {
        final var foundStock = this.findById(id);
        final var query = "DELETE FROM stock WHERE id = ?";
        try (var stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            var rows = stmt.executeUpdate();
            if (rows > 0){
                return foundStock;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Stock> findStockByRestaurantIdAndIngredientId(Integer ingredientId, Integer restaurantId) {
        final var query = "SELECT * FROM stock WHERE ingredient_id = ? AND restaurant_id = ?";
        try (final var stmt = connection.prepareStatement(query)){
            stmt.setInt(1, ingredientId);
            stmt.setInt(2, restaurantId);
            try (final var rs = stmt.executeQuery()){
                if (rs.next()){
                    return Optional.of(mapResutlSetToStock(rs));
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
    public Integer hasQuantityInsufficient(Integer restaurantId, List<QuantityIngredientRequest> quantityIngredients) {
        final var query = "SELECT * FROM stock WHERE ingredient_id = ? AND restaurant_id = ?";
        try (final var stmt = connection.prepareStatement(query)){
            for (var q: quantityIngredients){
                stmt.setInt(1, q.getIngredientId());
                stmt.setInt(2, restaurantId);
                var rs = stmt.executeQuery();
                while (rs.next()){
                    stmt.setInt(1, q.getIngredientId());
                    stmt.setInt(2, restaurantId);
                    var quantity = rs.getDouble("quantity");
                    if (quantity < q.getQuantity()){
                        return rs.getInt("ingredient_id");
                    }
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Stock mapResutlSetToStock(ResultSet rs) throws SQLException {
        return new Stock()
                .setId(rs.getInt("id"))
                .setIngredient(Ingredient.builder().id(rs.getInt("ingredient_id")).build())
                .setRestaurant(Restaurant.builder().id(rs.getInt("restaurant_id")).build())
                .setMovementType(MovementType.valueOf(rs.getString("movement_type")))
                .setQuantity(rs.getDouble("quantity"))
                .setMovementDate(rs.getTimestamp("movement_date").toInstant());
    }

    private void setStockParameters(PreparedStatement stmt, Stock stock) throws SQLException {
        stmt.setInt(1, stock.getRestaurant().getId());
        stmt.setInt(2, stock.getIngredient().getId());
        stmt.setString(3, String.valueOf(stock.getMovementType()));
        stmt.setDouble(4, stock.getQuantity());
        stmt.setTimestamp(5, Timestamp.from(stock.getMovementDate()));
    }
}
