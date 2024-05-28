package com.example.restorationmanagement.repositories.imp;


import com.example.restorationmanagement.entities.Unit;
import com.example.restorationmanagement.repositories.UnitRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UnitRepositoryImp implements UnitRepository {
    private final Connection connection;

    public UnitRepositoryImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Unit create(Unit toCreate) {
        final var query = "INSERT INTO unit (name) VALUES (?)";
        try (final var stmt = connection.prepareStatement(query)){
            setUnitParameters(stmt, toCreate);
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

    private void setUnitParameters(PreparedStatement stmt, Unit unit) throws SQLException {
    stmt.setString(1, unit.getName());
    }

    @Override
    public List<Unit> findAll() {
        final var query = "SELECT * FROM unit";
        List<Unit> units;
        try (final var stmt = connection.prepareStatement(query);
             final var resultSet = stmt.executeQuery()) {
            units = new ArrayList<>();
            while (resultSet.next()) {
                Unit unit = new Unit();
                unit.setId(resultSet.getInt("id"));
                unit.setName(resultSet.getString("name"));
                units.add(unit);
            }
            return units;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Unit> findById(Integer id) {
        final var query = "SELECT * FROM unit WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (final var resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Unit unit = new Unit();
                    unit.setId(resultSet.getInt("id"));
                    unit.setName(resultSet.getString("name"));
                    return Optional.of(unit);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

}
