package com.example.restorationmanagement.entities;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class MenuIngredient {
    private Menu menu;
    private Ingredient ingredient;
    private Double quantity;
}
