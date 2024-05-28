package com.example.restorationmanagement.repositories.imp;


import com.example.restorationmanagement.entities.Ingredient;
import com.example.restorationmanagement.entities.Menu;
import com.example.restorationmanagement.entities.MenuIngredient;
import com.example.restorationmanagement.repositories.IngredientRepository;
import com.example.restorationmanagement.repositories.MenuIngredientRepository;
import com.example.restorationmanagement.repositories.MenuRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MenuIngredientRepositoryImp implements MenuIngredientRepository {
    private final Connection connection;
    private final IngredientRepository ingredientRepository;
    private final MenuRepository menuRepository;

    public MenuIngredientRepositoryImp(Connection connection, IngredientRepository ingredientRepository, MenuRepository menuRepository) {
        this.connection = connection;
        this.ingredientRepository = ingredientRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public MenuIngredient create(MenuIngredient toCreate) {
        final var query = "INSERT INTO menuIngredient (menu_id, ingredient_id, quantity) VALUES (?, ?, ?)";
        try (final var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            setMenuIngredientParameters(stmt, toCreate);
           stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void setMenuIngredientParameters(PreparedStatement stmt, MenuIngredient menuIngredient) throws SQLException {
        stmt.setInt(1, menuIngredient.getMenu().getId());
        stmt.setInt(2, menuIngredient.getIngredient().getId());
        stmt.setDouble(3, menuIngredient.getQuantity());
    }

    @Override
    public List<MenuIngredient> findAll() {
        final var query = "SELECT * FROM menuIngredient";
        final var menuIngredients = new ArrayList<MenuIngredient>();
        try (final var stmt = connection.prepareStatement(query);
             final var rs = stmt.executeQuery()){
            while (rs.next()){
                var menuId = rs.getInt("menu_id");
                var ingredientId = rs.getInt("ingredient_id");
                var menuIngredient = mapResultSetToMenuIngredient(rs);
                menuIngredient.setIngredient(Ingredient.builder().id(ingredientId).build());
                menuIngredient.setMenu(Menu.builder().id(menuId).build());
                menuIngredients.add(menuIngredient);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return menuIngredients.stream().peek(m -> {
            m.setIngredient(ingredientRepository.findById(m.getIngredient().getId()).orElse(null));
            m.setMenu(menuRepository.findById(m.getMenu().getId()).orElse(null));
        }).collect(Collectors.toList());
    }

    private MenuIngredient mapResultSetToMenuIngredient(ResultSet rs) throws SQLException {
        return new MenuIngredient()
                .setMenu(Menu.builder().id(rs.getInt("menu_id")).build())
                .setIngredient(Ingredient.builder().id(rs.getInt("ingredient_id")).build())
                .setQuantity(rs.getDouble("quantity"));
    }
}