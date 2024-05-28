package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.entities.Sale;
import com.example.restorationmanagement.exception.BadRequestException;
import com.example.restorationmanagement.exception.InternalServerException;
import com.example.restorationmanagement.exception.NotFoundException;
import com.example.restorationmanagement.repositories.SaleRepository;
import com.example.restorationmanagement.service.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImp implements SaleService {
    private final SaleRepository saleRepository;

    public SaleServiceImp(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public Sale createSale(Sale sale) {
        try {
            if (sale.getId() == null){
                return saleRepository.create(sale);
            }
            throw new BadRequestException("Sale ID must be null for creation");
        } catch (BadRequestException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public List<Sale> findAll() {
        try {
            return saleRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Sale findSaleById(Integer id) {
        try {
            return saleRepository.findById(id).orElseThrow(() -> new NotFoundException("Sale not found"));
        } catch (NotFoundException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Sale updateSale(Integer id, Sale sale) {
        try {
            final var foundSaleOptional = saleRepository.findById(id);
            if (foundSaleOptional.isPresent()){
                var foundSale = foundSaleOptional.get();
                foundSale.setRestaurant(sale.getRestaurant());
                foundSale.setMenu(sale.getMenu());
                foundSale.setSaleDate(sale.getSaleDate());
                foundSale.setQuantity(sale.getQuantity());
                foundSale.setPrice(sale.getPrice());
                return saleRepository.update(foundSale);
            }
            throw new NotFoundException("Sale not found");
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Sale destroySaleById(Integer id) {
        try {
            return saleRepository.delete(id).orElseThrow(() -> new NotFoundException("Sale not found"));
        } catch (NotFoundException e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
