package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.entities.Unit;
import com.example.restorationmanagement.repositories.UnitRepository;
import org.springframework.stereotype.Service;
import com.example.restorationmanagement.service.UnitService;

import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImp implements UnitService {
    private UnitRepository unitRepository;

    public UnitServiceImp(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }
    

    @Override
    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    @Override
    public Optional<Unit> findById(int id) {
        return unitRepository.findById(id);
    }

    @Override
    public Unit save(Unit unit) {
        return unitRepository.create(unit);
    }

}

