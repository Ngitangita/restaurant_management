package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.entities.Ingredient;
import com.example.restorationmanagement.exception.BadRequestException;
import com.example.restorationmanagement.exception.InternalServerException;
import com.example.restorationmanagement.exception.NotFoundException;
import com.example.restorationmanagement.repositories.IngredientRepository;
import com.example.restorationmanagement.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IngredientServiceImp implements IngredientService {
   private final IngredientRepository ingredientRepository;

    public IngredientServiceImp(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        try {
            if (ingredient.getId() == null){
                return ingredientRepository.create((ingredient));
            }
            throw new BadRequestException("Ingredient ID must be null for creation");
        } catch (BadRequestException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Ingredient findIngredientById(Integer id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new NotFoundException("Ingredient not found"));

    }

    @Override
    public List<Ingredient> findAll() {
        try {
            return ingredientRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Ingredient updateIngredient(Integer id, Ingredient ingredient) {
        try {
            final var foundIngredientOptional = ingredientRepository.findById(id);
            if (foundIngredientOptional.isPresent()){
                var foundIngredient = foundIngredientOptional.get();
                foundIngredient.setName(ingredient.getName());
                foundIngredient.setCostPrice(ingredient.getCostPrice());
                foundIngredient.setQuantity(ingredient.getQuantity());
                foundIngredient.setUnit(ingredient.getUnit());
                return ingredientRepository.update(foundIngredient);
            }
            throw new NotFoundException("Ingredient not found");
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Ingredient destroyIngredientById(Integer id) {
        try {
            return ingredientRepository.delete(id).orElseThrow(() -> new NotFoundException("Ingredient not found"));
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
