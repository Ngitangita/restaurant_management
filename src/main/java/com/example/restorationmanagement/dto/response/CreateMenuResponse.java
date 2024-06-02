package com.example.restorationmanagement.dto.response;

import com.example.restorationmanagement.dto.request.QuantityIngredientRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateMenuResponse {
    private Integer menuId;
    private List<QuantityIngredientRequest> quantityIngredients;
}
