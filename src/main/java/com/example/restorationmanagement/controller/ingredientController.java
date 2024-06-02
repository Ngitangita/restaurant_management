package com.example.restorationmanagement.controller;

import com.example.restorationmanagement.dto.request.IngredientRequest;
import com.example.restorationmanagement.dto.response.IngredientResponse;
import com.example.restorationmanagement.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredients")
public class ingredientController {
    private final IngredientService ingredientService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientResponse createIngredient(@RequestBody IngredientRequest ingredientRequest){
        return ingredientService.create(ingredientRequest);
    }

}
