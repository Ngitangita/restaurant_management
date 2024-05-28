package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.dto.DataSynthesis;
import com.example.restorationmanagement.repositories.DataSynthesisRepository;
import com.example.restorationmanagement.service.DataSynthesisService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataSynthesisServiceImp implements DataSynthesisService {
    private final DataSynthesisRepository dataSynthesisRepository;

    public DataSynthesisServiceImp(DataSynthesisRepository dataSynthesisRepository) {
        this.dataSynthesisRepository = dataSynthesisRepository;
    }

    @Override
    public List<DataSynthesis> findAll() {
        try {
            return dataSynthesisRepository.findDataSynthesis();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
