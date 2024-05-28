package com.example.restorationmanagement.controller;

import com.example.restorationmanagement.entities.Ingredient;
import com.example.restorationmanagement.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Ingredient createIngredient(@RequestBody Ingredient ingredient){
        return ingredientService.createIngredient(ingredient);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredientById(@PathVariable Integer id){
        return ingredientService.findIngredientById(id);
    }

    @GetMapping
    public List<Ingredient> getAllIngredients(){
        return ingredientService.findAll();
    }

    @PutMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable Integer id, @RequestBody Ingredient ingredient){
        return ingredientService.updateIngredient(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public Ingredient deleteIngredient(@PathVariable Integer id){
        return ingredientService.destroyIngredientById(id);
    }
}
