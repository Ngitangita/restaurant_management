package com.example.restorationmanagement.controller;

import com.example.restorationmanagement.dto.request.MenuRequest;
import com.example.restorationmanagement.dto.response.MenuResponse;
import com.example.restorationmanagement.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("menus")
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MenuResponse createMenu(@RequestBody MenuRequest menuRequest){
        return menuService.create(menuRequest);
    }

}
