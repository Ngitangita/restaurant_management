package com.example.restorationmanagement.repositories;

import com.example.restorationmanagement.entities.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {
    Restaurant create(Restaurant toCreate);
    List<Restaurant> findAll();
    Optional<Restaurant> findById(Integer id);
}
