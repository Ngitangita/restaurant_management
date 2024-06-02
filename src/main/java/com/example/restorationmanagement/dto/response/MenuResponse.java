package com.example.restorationmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MenuResponse {
    private Integer id;
    private String name;
    private Double currentPrice;
}
