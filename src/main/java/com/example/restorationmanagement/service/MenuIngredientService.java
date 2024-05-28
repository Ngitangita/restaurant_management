package com.example.restorationmanagement.service;

import com.example.restorationmanagement.entities.MenuIngredient;

import java.util.List;

public interface MenuIngredientService {
    MenuIngredient createMenuIngredient(MenuIngredient menuIngredient);
    List<MenuIngredient> findAll();
}
