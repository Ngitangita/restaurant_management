package com.example.restorationmanagement.controller;

import com.example.restorationmanagement.dto.request.RestaurantRequest;
import com.example.restorationmanagement.dto.response.RestaurantResponse;
import com.example.restorationmanagement.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantResponse creatRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.create(restaurantRequest);
    }
}
