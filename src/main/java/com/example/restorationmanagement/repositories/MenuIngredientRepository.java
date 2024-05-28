package com.example.restorationmanagement.repositories;

import com.example.restorationmanagement.entities.MenuIngredient;

import java.util.List;
import java.util.Optional;

public interface MenuIngredientRepository {
    MenuIngredient create(MenuIngredient toCreate);
    List<MenuIngredient> findAll();
}
