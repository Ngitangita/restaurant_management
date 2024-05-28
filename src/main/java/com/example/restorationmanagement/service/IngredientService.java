package com.example.restorationmanagement.service;

import com.example.restorationmanagement.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient createIngredient(Ingredient ingredient);
    Ingredient findIngredientById(Integer id);
    List<Ingredient> findAll();
    Ingredient updateIngredient(Integer id, Ingredient ingredient);
    Ingredient destroyIngredientById(Integer id);
}
