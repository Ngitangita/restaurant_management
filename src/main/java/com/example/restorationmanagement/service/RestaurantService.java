package com.example.restorationmanagement.service;

import com.example.restorationmanagement.dto.request.RestaurantRequest;
import com.example.restorationmanagement.dto.response.RestaurantResponse;

public interface RestaurantService {
    RestaurantResponse create(RestaurantRequest toCreate);
}
