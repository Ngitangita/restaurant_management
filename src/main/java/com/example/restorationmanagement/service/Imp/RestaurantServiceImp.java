package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.entities.Restaurant;
import com.example.restorationmanagement.exception.BadRequestException;
import com.example.restorationmanagement.exception.InternalServerException;
import com.example.restorationmanagement.exception.NotFoundException;
import com.example.restorationmanagement.repositories.RestaurantRepository;
import com.example.restorationmanagement.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImp implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImp(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        try {
            if (restaurant.getId() == null){
                return restaurantRepository.create(restaurant);
            }
            throw new BadRequestException("Restaurant ID must be null for creation");
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public List<Restaurant> findAll() {
        try {
            return restaurantRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Restaurant findRestaurantById(Integer id) {
        try {
            return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant not found"));
        } catch (NotFoundException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

}
