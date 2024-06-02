package com.example.restorationmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateMenuRequest {
    private Integer menuId;
    private Integer restaurantId;
    private List<QuantityIngredientRequest> quantityIngredients;
}
