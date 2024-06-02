package com.example.restorationmanagement.service;

import com.example.restorationmanagement.dto.request.IngredientRequest;
import com.example.restorationmanagement.dto.response.IngredientResponse;

public interface IngredientService {
    IngredientResponse create(IngredientRequest toCreate);
}
