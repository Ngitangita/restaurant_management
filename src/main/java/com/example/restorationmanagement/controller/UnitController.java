package com.example.restorationmanagement.controller;

import com.example.restorationmanagement.entities.Unit;
import com.example.restorationmanagement.service.UnitService;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Accessors
@RequestMapping("/units")
public class UnitController {
    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public List<Unit> findAll() {
        return unitService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Unit> findById(@PathVariable int id) {
        return unitService.findById(id);
    }

    @PostMapping("/create")
    public Unit save(@RequestBody Unit unit) {
        return unitService.save(unit);
    }
}
