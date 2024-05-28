package com.example.restorationmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalViewOfSalesData {
    Integer ingredient_id;
    String ingredient_name;
    String menu_name;
    Double total_quantity_used;
    String unit_name;

    public GlobalViewOfSalesData(Integer ingredient_id, String ingredient_name, String menu_name, Double total_quantity_used, String unit_name) {
        this.ingredient_id = ingredient_id;
        this.ingredient_name = ingredient_name;
        this.menu_name = menu_name;
        this.total_quantity_used = total_quantity_used;
        this.unit_name = unit_name;
    }

}
