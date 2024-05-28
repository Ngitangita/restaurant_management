package com.example.restorationmanagement.controller;

import com.example.restorationmanagement.entities.Menu;
import com.example.restorationmanagement.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Menu createMenu(@RequestBody Menu menu){
        return menuService.createdMenu(menu);
    }

    @GetMapping
    public List<Menu> getAllMenus(){
        return menuService.findAll();
    }

    @GetMapping("/{id}")
    public Menu getMenuById(@PathVariable Integer id){
        return menuService.findMenuById(id);
    }
}
