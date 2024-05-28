package com.example.restorationmanagement.service;

import com.example.restorationmanagement.entities.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant createRestaurant(Restaurant restaurant);
    List<Restaurant> findAll();
    Restaurant findRestaurantById(Integer id);
}
