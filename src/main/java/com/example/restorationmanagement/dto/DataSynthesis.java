package com.example.restorationmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataSynthesis {
    Integer restaurant_id;
    String restaurant_location;
    Double menu_1_quantity;
    Double menu_2_quantity;
    Double menu_3_quantity;
    Double menu_1_revenue;
    Double menu_2_revenue;
    Double menu_3_revenue;

    public DataSynthesis(Integer restaurant_id, String restaurant_location, Double menu_1_quantity, Double menu_2_quantity, Double menu_3_quantity, Double menu_1_revenue, Double menu_2_revenue, Double menu_3_revenue) {
        this.restaurant_id = restaurant_id;
        this.restaurant_location = restaurant_location;
        this.menu_1_quantity = menu_1_quantity;
        this.menu_2_quantity = menu_2_quantity;
        this.menu_3_quantity = menu_3_quantity;
        this.menu_1_revenue = menu_1_revenue;
        this.menu_2_revenue = menu_2_revenue;
        this.menu_3_revenue = menu_3_revenue;
    }
}
