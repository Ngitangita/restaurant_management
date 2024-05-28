package com.example.restorationmanagement.repositories.imp;

import com.example.restorationmanagement.entities.Menu;
import com.example.restorationmanagement.repositories.MenuRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MenuRepositoryImp implements MenuRepository {
    private final Connection connection;

    public MenuRepositoryImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Menu create(Menu toCreate) {
        final var query = "INSERT INTO menu (name, currentPrice) VALUES (?, ?)";
        try (final var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            setMenuParameters(stmt, toCreate);
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

    private void setMenuParameters(PreparedStatement stmt, Menu menu) throws SQLException {
        stmt.setString(1, menu.getName());
        stmt.setDouble(2, menu.getCurrentPrice());
    }

    @Override
    public List<Menu> findAll() {
        final var query = "SELECT * FROM menu";
        final var menus = new ArrayList<Menu>();
        try (final var stmt = connection.prepareStatement(query);
             final var rs = stmt.executeQuery()){
            while (rs.next()){
                menus.add(mapResultSetToMenu(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return menus;
    }


    private Menu mapResultSetToMenu(ResultSet rs) throws SQLException {
        return new Menu()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setCurrentPrice(rs.getDouble("currentPrice"));

    }

    @Override
    public Optional<Menu> findById(Integer id) {
        final var query = "SELECT * FROM menu WHERE id = ?";
        try (final var stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()){
                if (rs.next()){
                    return Optional.of(mapResultSetToMenu(rs));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

}
