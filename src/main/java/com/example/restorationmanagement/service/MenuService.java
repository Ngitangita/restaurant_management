package com.example.restorationmanagement.service;

import com.example.restorationmanagement.dto.request.MenuRequest;
import com.example.restorationmanagement.dto.response.MenuResponse;

public interface MenuService {
    MenuResponse create(MenuRequest toCreate);
}
