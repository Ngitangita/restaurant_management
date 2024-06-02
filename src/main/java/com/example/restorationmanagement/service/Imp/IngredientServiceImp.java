package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.dto.request.IngredientRequest;
import com.example.restorationmanagement.dto.response.IngredientResponse;
import com.example.restorationmanagement.entities.Ingredient;
import com.example.restorationmanagement.entities.Stock;
import com.example.restorationmanagement.entities.Unit;
import com.example.restorationmanagement.enumes.MovementType;
import com.example.restorationmanagement.exception.NotFoundException;
import com.example.restorationmanagement.repositories.IngredientRepository;
import com.example.restorationmanagement.repositories.RestaurantRepository;
import com.example.restorationmanagement.repositories.StockRepository;
import com.example.restorationmanagement.repositories.UnitRepository;
import com.example.restorationmanagement.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class IngredientServiceImp implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final StockRepository stockRepository;
    private final RestaurantRepository restaurantRepository;
    private final UnitRepository unitRepository;

    @Override
    public IngredientResponse create(IngredientRequest toCreate) {
        var ingredient = Ingredient.builder()
                .name(toCreate.getName())
                .costPrice(toCreate.getCostPrice())
                .quantity(toCreate.getQuantity())
                .build();

        if (toCreate.getUnitName() != null && toCreate.getUnitId() != null){
            throw new NotFoundException("Unit invalid");
        }

        if (toCreate.getUnitName() != null) {
            var savedUnit = unitRepository.create(Unit.builder().name(toCreate.getUnitName()).build());
            ingredient.setUnit(savedUnit);
        }

        if (toCreate.getUnitId() != null) {
            var savedUnit = unitRepository.findById(toCreate.getUnitId()).orElseThrow(() -> new NotFoundException("Unit not found"));
            ingredient.setUnit(savedUnit);
        }

        Ingredient savedIngredient = ingredientRepository.create(ingredient);
        var restaurants = restaurantRepository.findAll();
        for (var restaurant: restaurants){
            var stock = Stock.builder()
                    .restaurant(restaurant)
                    .ingredient(savedIngredient)
                    .quantity(00.0)
                    .movementType(MovementType.ENTREE)
                    .movementDate(Instant.now())
                    .build();
            stockRepository.create(stock);
        }

        return IngredientResponse.builder()
                .name(savedIngredient.getName())
                .id(savedIngredient.getId())
                .costPrice(savedIngredient.getCostPrice())
                .quantity(savedIngredient.getQuantity())
                .build();
    }
}
