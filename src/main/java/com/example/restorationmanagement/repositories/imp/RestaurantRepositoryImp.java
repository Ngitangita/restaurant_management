package com.example.restorationmanagement.repositories.imp;

import com.example.restorationmanagement.entities.Restaurant;
import com.example.restorationmanagement.repositories.RestaurantRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantRepositoryImp implements RestaurantRepository {
    private final Connection connection;

    public RestaurantRepositoryImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Restaurant create(Restaurant toCreate) {
        final var query = "INSERT INTO restaurant (location) VALUES (?)";
        try (final var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            setRestaurantParameters(stmt, toCreate);
            final int rows = stmt.executeUpdate();
            if (rows > 0){
                try ( var generatedKeys = stmt.getGeneratedKeys()){
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

    private void setRestaurantParameters(PreparedStatement stmt, Restaurant restaurant) throws SQLException {
        stmt.setString(1, restaurant.getLocation());
    }

    @Override
    public List<Restaurant> findAll() {
        final var query = "SELECT * FROM restaurant";
        final var restaurants = new ArrayList<Restaurant>();
        try (final var stmt = connection.prepareStatement(query);
            final var rs = stmt.executeQuery()){
            while (rs.next()){
                restaurants.add(mapResulteSetToRestaurant(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return restaurants;
    }

    private Restaurant mapResulteSetToRestaurant(ResultSet rs) throws SQLException {
        return new Restaurant()
                .setId(rs.getInt("id"))
                .setLocation(rs.getString("location"));

    }

    @Override
    public Optional<Restaurant> findById(Integer id) {
        final var query = "SELECT * FROM restaurant WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()){
                if (rs.next()){
                    return Optional.of(mapResulteSetToRestaurant(rs));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

}
