package com.example.restorationmanagement.dto;

import com.example.restorationmanagement.entities.Ingredient;
import com.example.restorationmanagement.entities.Unit;
import com.example.restorationmanagement.enumes.MovementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockManagement {
    private String ingredient;
    private MovementType movementType;
    private Double quantity;
    private Instant movementDate;
    private String unit;

}
