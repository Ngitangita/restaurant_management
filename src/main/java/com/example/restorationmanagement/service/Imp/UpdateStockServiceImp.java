package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.dto.request.UpdateStockRequest;
import com.example.restorationmanagement.dto.response.UpdateStockResponse;
import com.example.restorationmanagement.exception.NotFoundException;
import com.example.restorationmanagement.repositories.StockRepository;
import com.example.restorationmanagement.service.UpdateStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateStockServiceImp implements UpdateStockService {
    private final StockRepository stockRepository;

    @Override
    public UpdateStockResponse update(UpdateStockRequest toUpdate) {
        var stocked = stockRepository.findStockByRestaurantIdAndIngredientId(toUpdate.getIngredientId(), toUpdate.getRestaurantId())
                .orElseThrow(() -> new NotFoundException("Restaurant or ingredient not found"));
        var newQuantity = stocked.getQuantity() + toUpdate.getQuantity();
        stocked.setQuantity(newQuantity);
        stockRepository.update(stocked);
        return new UpdateStockResponse(toUpdate.getIngredientId(), toUpdate.getRestaurantId(), stocked.getQuantity());
    }
}
