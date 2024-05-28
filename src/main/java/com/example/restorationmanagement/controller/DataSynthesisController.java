package com.example.restorationmanagement.controller;

import com.example.restorationmanagement.dto.DataSynthesis;
import com.example.restorationmanagement.service.DataSynthesisService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping
public class DataSynthesisController {
    private final DataSynthesisService dataSynthesisService;

    public DataSynthesisController(DataSynthesisService dataSynthesisService) {
        this.dataSynthesisService = dataSynthesisService;
    }


    @GetMapping("/dataSynthesis")
    public List<DataSynthesis> findAllDataSynthesisRepositoryImp(){
        return dataSynthesisService.findAll();
    }
}
