package com.example.restorationmanagement.repositories;

import com.example.restorationmanagement.entities.Unit;

import java.util.List;
import java.util.Optional;

public interface UnitRepository {
    Unit create(Unit toCreate);
    List<Unit> findAll();
    Optional<Unit> findById(Integer id);

}
