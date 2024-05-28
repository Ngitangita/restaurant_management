package com.example.restorationmanagement.service;

import com.example.restorationmanagement.entities.Menu;

import java.util.List;

public interface MenuService {
    Menu createdMenu(Menu menu);
    List<Menu> findAll();
    Menu findMenuById(Integer id);
}
