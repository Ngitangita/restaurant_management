package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.entities.MenuIngredient;
import com.example.restorationmanagement.exception.BadRequestException;
import com.example.restorationmanagement.exception.InternalServerException;
import com.example.restorationmanagement.exception.NotFoundException;
import com.example.restorationmanagement.repositories.MenuIngredientRepository;
import com.example.restorationmanagement.service.MenuIngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuIngredientServiceImp implements MenuIngredientService {
    private final MenuIngredientRepository menuIngredientRepository;

    public MenuIngredientServiceImp(MenuIngredientRepository menuIngredientRepository) {
        this.menuIngredientRepository = menuIngredientRepository;
    }

    @Override
    public MenuIngredient createMenuIngredient(MenuIngredient menuIngredient) {
       try {
           if (menuIngredient.getIngredient() != null && menuIngredient.getMenu() != null){
               return menuIngredientRepository.create(menuIngredient);
           }
           throw new BadRequestException("MenuIngredient Id must be null for creation");
       } catch (Exception e) {
           throw new InternalServerException(e.getMessage());
       }
    }

    @Override
    public List<MenuIngredient> findAll() {
        try {
            return menuIngredientRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
