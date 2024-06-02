package com.example.restorationmanagement.controller;

import com.example.restorationmanagement.dto.request.CreateMenuRequest;
import com.example.restorationmanagement.dto.response.CreateMenuResponse;
import com.example.restorationmanagement.service.CreateMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/createMenus")
public class CreateMenuController {
    private final CreateMenuService createMenuService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMenuResponse createMenuResponse(@RequestBody CreateMenuRequest createMenuRequest){
        return createMenuService.create(createMenuRequest);
    }

}
