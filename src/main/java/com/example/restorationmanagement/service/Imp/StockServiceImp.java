package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.entities.Stock;
import com.example.restorationmanagement.exception.BadRequestException;
import com.example.restorationmanagement.exception.InternalServerException;
import com.example.restorationmanagement.exception.NotFoundException;
import com.example.restorationmanagement.repositories.StockRepository;
import com.example.restorationmanagement.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImp implements StockService {
    private final StockRepository stockRepository;

    public StockServiceImp(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock createStock(Stock stock) {
        try {
            if (stock.getId() == null){
                return stockRepository.create(stock);
            }
            throw new BadRequestException("Stock ID must be null for creation");
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public List<Stock> findAll() {
        try {
            return stockRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Stock findStockById(Integer id) {
        try {
            return stockRepository.findById(id).orElseThrow(() -> new NotFoundException("Stock not found"));
        } catch (NotFoundException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Stock updateStock(Integer id, Stock stock) {
        try {
            final var foundStockOptional = stockRepository.findById(id);
            if (foundStockOptional.isPresent()){
                var foundStock = foundStockOptional.get();
                foundStock.setRestaurant(stock.getRestaurant());
                foundStock.setIngredient(stock.getIngredient());
                foundStock.setMovementType(stock.getMovementType());
                foundStock.setQuantity(stock.getQuantity());
                foundStock.setMovementDate(stock.getMovementDate());
                return stockRepository.update(foundStock);
            }
            throw new NotFoundException("Stock not found");
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public Stock destroyStockById(Integer id) {
        try {
            return stockRepository.delete(id).orElseThrow(() -> new NotFoundException("Stock not found"));
        } catch (NotFoundException e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
