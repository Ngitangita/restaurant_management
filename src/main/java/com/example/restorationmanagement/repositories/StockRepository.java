package com.example.restorationmanagement.repositories;

import com.example.restorationmanagement.dto.request.QuantityIngredientRequest;
import com.example.restorationmanagement.entities.Stock;

import java.util.List;
import java.util.Optional;

public interface StockRepository {
    Stock create(Stock toCreate);
    List<Stock> findAll();
    Optional<Stock> findById(Integer id);
    Stock update(Stock toUpdate);
    Optional<Stock> delete(Integer id);
    Optional<Stock> findStockByRestaurantIdAndIngredientId(Integer ingredientId, Integer restaurantId);

    Integer hasQuantityInsufficient(Integer restaurantId, List<QuantityIngredientRequest> quantityIngredients);
}
