package com.example.restorationmanagement.repositories;

import com.example.restorationmanagement.entities.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {
    Menu create(Menu toCreate);
    List<Menu> findAll();
    Optional<Menu> findById(Integer id);
}
