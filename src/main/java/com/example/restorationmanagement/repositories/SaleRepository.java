package com.example.restorationmanagement.repositories;

import com.example.restorationmanagement.entities.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository {
    Sale create(Sale toCreate);
    List<Sale> findAll();
    Optional<Sale> findById(Integer id);
    Sale update(Sale toUpdate);
    Optional<Sale> delete(Integer id);
}
