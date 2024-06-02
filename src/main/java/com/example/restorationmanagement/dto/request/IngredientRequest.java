package com.example.restorationmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class IngredientRequest {
    private Integer id;
    private String name;
    private Double quantity;
    private Double costPrice;
    private Integer unitId;
    private String unitName;
}
