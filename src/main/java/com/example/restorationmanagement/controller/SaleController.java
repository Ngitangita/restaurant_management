package com.example.restorationmanagement.controller;

import com.example.restorationmanagement.entities.Sale;
import com.example.restorationmanagement.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Sale createSale(@RequestBody Sale sale){
        return saleService.createSale(sale);
    }

    @GetMapping
    public List<Sale> getAllSale(){
        return saleService.findAll();
    }

    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable Integer id){
        return saleService.findSaleById(id);
    }

    @PutMapping("/{id}")
    public Sale updateSale(@PathVariable Integer id, @RequestBody Sale sale){
        return saleService.updateSale(id, sale);
    }

    @DeleteMapping("/{id}")
    public Sale deleteSale(@PathVariable Integer id){
        return saleService.destroySaleById(id);
    }
}
