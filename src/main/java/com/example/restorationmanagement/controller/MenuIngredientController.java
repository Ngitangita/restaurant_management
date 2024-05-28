package com.example.restorationmanagement.controller;

import com.example.restorationmanagement.entities.MenuIngredient;
import com.example.restorationmanagement.service.MenuIngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/menuIngredients")
public class MenuIngredientController {
    private final MenuIngredientService menuIngredientService;

    public MenuIngredientController(MenuIngredientService menuIngredientService) {
        this.menuIngredientService = menuIngredientService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MenuIngredient createMenuIngredient(@RequestBody MenuIngredient menuIngredient){
        return menuIngredientService.createMenuIngredient(menuIngredient);
    }

    @GetMapping
    public List<MenuIngredient> getAllMenuIngredients(){
        return menuIngredientService.findAll();
    }
}
