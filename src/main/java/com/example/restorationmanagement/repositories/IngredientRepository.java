package com.example.restorationmanagement.repositories;


import com.example.restorationmanagement.entities.Ingredient;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IngredientRepository {
    Ingredient create (Ingredient toCreate);
    Optional<Ingredient> findById(Integer id);
    List<Ingredient> findAll();
    Ingredient update(Ingredient toUpdate);
    Optional<Ingredient> delete(Integer id);

}
