package com.example.restorationmanagement.controller;

import com.example.restorationmanagement.entities.Stock;
import com.example.restorationmanagement.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/stocks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Stock createStock(@RequestBody Stock stock){
        return stockService.createStock(stock);
    }

    @GetMapping
    public List<Stock> getAllStock(){
        return stockService.findAll();
    }

    @GetMapping("/{id}")
    public Stock getStockById(@PathVariable Integer id){
        return stockService.findStockById(id);
    }

    @PutMapping("/{id}")
    public Stock updateStock(@PathVariable Integer id, @RequestBody Stock stock){
        return stockService.updateStock(id, stock);
    }

    @DeleteMapping("/{id}")
    public Stock deleteStock(@PathVariable Integer id){
        return stockService.destroyStockById(id);
    }
}
