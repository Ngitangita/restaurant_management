package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.dto.request.CreateMenuRequest;
import com.example.restorationmanagement.dto.response.CreateMenuResponse;
import com.example.restorationmanagement.entities.Ingredient;
import com.example.restorationmanagement.entities.Menu;
import com.example.restorationmanagement.entities.MenuIngredient;
import com.example.restorationmanagement.enumes.MovementType;
import com.example.restorationmanagement.exception.BadRequestException;
import com.example.restorationmanagement.repositories.MenuIngredientRepository;
import com.example.restorationmanagement.repositories.StockRepository;
import com.example.restorationmanagement.service.CreateMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CreateMenuServiceImp implements CreateMenuService {
    private final StockRepository stockRepository;
    private final MenuIngredientRepository menuIngredientRepository;

    @Override
    public CreateMenuResponse create(CreateMenuRequest toCreate) {
        var hasQuantityInsufficient = stockRepository.hasQuantityInsufficient(toCreate.getRestaurantId(),toCreate.getQuantityIngredients());
        if (hasQuantityInsufficient != null){
            throw new BadRequestException("Stock of the ingredient id: " + hasQuantityInsufficient + " insufficient...");
        }
        for (var q: toCreate.getQuantityIngredients()){
            var stock = stockRepository.findStockByRestaurantIdAndIngredientId(q.getIngredientId(), toCreate.getRestaurantId()).orElseThrow();
            var menuIngredient = MenuIngredient.builder()
                    .menu(Menu.builder().id(toCreate.getMenuId()).build())
                    .ingredient(Ingredient.builder().id(q.getIngredientId()).build())
                    .quantity(q.getQuantity())
                    .build();
            var newQuantity = stock.getQuantity() - q.getQuantity();
            stock.setQuantity(newQuantity);
            stock.setMovementDate(Instant.now());
            stock.setMovementType(MovementType.SORTIE);
            menuIngredientRepository.create(menuIngredient);
            stockRepository.update(stock);

        }

        return new CreateMenuResponse(toCreate.getMenuId(), toCreate.getQuantityIngredients());
    }
}
