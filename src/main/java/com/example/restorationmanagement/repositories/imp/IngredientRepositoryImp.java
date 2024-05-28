package com.example.restorationmanagement.repositories.imp;

import com.example.restorationmanagement.entities.Ingredient;
import com.example.restorationmanagement.entities.Unit;
import com.example.restorationmanagement.repositories.IngredientRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientRepositoryImp implements IngredientRepository {
    private final Connection connection;

    public IngredientRepositoryImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Ingredient create(Ingredient toCreate)  {
        final var query = "INSERT INTO ingredient (name, costPrice, quantity, unit_id) VALUES (?, ?, ?, ?)";
        try {
            final var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            setIngredientParameters(stmt, toCreate);
            final var rows = stmt.executeUpdate();
            if (rows > 0){
                try (final var generatedKeys = stmt.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        final var id = generatedKeys.getInt(1);
                        return findById(id).orElse(null);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Optional<Ingredient> findById(Integer id) {
        final var query = "SELECT * FROM ingredient WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()){
                if (rs.next()){
                    return Optional.of(mapResultSetToIngredient(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public List<Ingredient> findAll() {
        final var query = "SELECT * FROM ingredient";
        final var ingredients = new ArrayList<Ingredient>();
        try (final var stmt = connection.prepareStatement(query);
             final var rs = stmt.executeQuery()){
            while (rs.next()){
                ingredients.add(mapResultSetToIngredient(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ingredients;
    }

    @Override
    public Ingredient update(Ingredient toUpdate) {
        final var query = "UPDATE ingredient SET name = ?, costPrice = ?, quantity = ?, unit_id = ? WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)){
            setIngredientParameters(stmt, toUpdate);
            stmt.setInt(5, toUpdate.getId());
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
    public Optional<Ingredient> delete(Integer id) {
        final var foundIngredient = this.findById(id);
        final var query = "DELETE FROM ingredient WHERE id = ?";
        try (var stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            var rows = stmt.executeUpdate();
            if (rows > 0){
                return foundIngredient;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    private Ingredient mapResultSetToIngredient(ResultSet rs) throws SQLException {
        return new Ingredient()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setCostPrice((rs.getDouble("costPrice")))
                .setQuantity(rs.getDouble("quantity"))
                .setUnit(Unit.builder().id(rs.getInt("unit_id")).build());
    }

    private void setIngredientParameters(PreparedStatement stmt, Ingredient ingredient) throws SQLException {
        stmt.setString(1, ingredient.getName());
        stmt.setDouble(2, ingredient.getCostPrice());
        stmt.setDouble(3, ingredient.getQuantity());
        stmt.setInt(4, ingredient.getUnit().getId());
    }

}
