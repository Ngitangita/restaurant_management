package com.example.restorationmanagement.service;

import com.example.restorationmanagement.dto.request.CreateMenuRequest;
import com.example.restorationmanagement.dto.response.CreateMenuResponse;

public interface CreateMenuService {
    CreateMenuResponse create(CreateMenuRequest toCreate);
}
