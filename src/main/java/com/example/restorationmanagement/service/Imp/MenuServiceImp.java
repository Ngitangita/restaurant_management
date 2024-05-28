package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.entities.Menu;
import com.example.restorationmanagement.exception.BadRequestException;
import com.example.restorationmanagement.exception.InternalServerException;
import com.example.restorationmanagement.exception.NotFoundException;
import com.example.restorationmanagement.repositories.MenuRepository;
import com.example.restorationmanagement.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImp implements MenuService {
    private final MenuRepository menuRepository;

    public MenuServiceImp(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Menu createdMenu(Menu menu) {
        try {
            if (menu.getId() == null){
                return menuRepository.create((menu));
            }
            throw new BadRequestException("Menu ID must be null for creation");
        } catch (BadRequestException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public List<Menu> findAll() {
        try {
            return menuRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Menu findMenuById(Integer id) {
        try {
            return menuRepository.findById(id).orElseThrow(() -> new NotFoundException("Menu not found"));
        } catch (NotFoundException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

}
