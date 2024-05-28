package com.example.restorationmanagement.service;

import com.example.restorationmanagement.entities.Unit;

import java.util.List;
import java.util.Optional;

public interface UnitService {
    List<Unit> findAll();
    Optional<Unit> findById(int id);
    Unit save(Unit unit);
}
