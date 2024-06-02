package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.dto.request.RestaurantRequest;
import com.example.restorationmanagement.dto.response.RestaurantResponse;
import com.example.restorationmanagement.entities.Restaurant;
import com.example.restorationmanagement.entities.Stock;
import com.example.restorationmanagement.enumes.MovementType;
import com.example.restorationmanagement.repositories.IngredientRepository;
import com.example.restorationmanagement.repositories.RestaurantRepository;
import com.example.restorationmanagement.repositories.StockRepository;
import com.example.restorationmanagement.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class RestaurantServiceImp implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final IngredientRepository ingredientRepository;
    private final StockRepository stockRepository;



    @Override
    public RestaurantResponse create(RestaurantRequest restaurantRequest) {
        var restaurant = Restaurant.builder().location(restaurantRequest.getLocation()).build();
        var savedRestaurant = restaurantRepository.create(restaurant);
        var ingredients = ingredientRepository.findAll();

        for (var ingredient: ingredients){
            var stock = Stock.builder()
                    .restaurant(savedRestaurant)
                    .ingredient(ingredient)
                    .quantity(00.0)
                    .movementType(MovementType.ENTREE)
                    .movementDate(Instant.now())
                    .build();
            stockRepository.create(stock);
        }
     return new RestaurantResponse(savedRestaurant.getId(), savedRestaurant.getLocation());
    }


}
