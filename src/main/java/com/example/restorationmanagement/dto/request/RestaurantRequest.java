package com.example.restorationmanagement.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RestaurantRequest {
    private Integer id;
    private String location;
}
