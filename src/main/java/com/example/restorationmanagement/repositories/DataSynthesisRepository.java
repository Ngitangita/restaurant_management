package com.example.restorationmanagement.repositories;

import com.example.restorationmanagement.dto.DataSynthesis;

import java.util.List;

public interface DataSynthesisRepository {
    List<DataSynthesis> findDataSynthesis();
}
