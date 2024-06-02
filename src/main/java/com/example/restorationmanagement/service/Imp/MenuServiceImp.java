package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.dto.request.MenuRequest;
import com.example.restorationmanagement.dto.response.MenuResponse;
import com.example.restorationmanagement.entities.Menu;
import com.example.restorationmanagement.exception.InternalServerException;
import com.example.restorationmanagement.repositories.MenuRepository;
import com.example.restorationmanagement.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuServiceImp implements MenuService {
    private final MenuRepository menuRepository;

    @Override
    public MenuResponse create(MenuRequest menuRequest) {
        var menu = Menu.builder()
                .name(menuRequest.getName())
                .currentPrice(menuRequest.getCurrentPrice()).build();
        try {
            if (menuRequest.getId() == null){
                var saved =  menuRepository.create(menu);
                return new MenuResponse(saved.getId(), saved.getName(), saved.getCurrentPrice());
            }
            throw new InternalServerException("menu not found");
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
