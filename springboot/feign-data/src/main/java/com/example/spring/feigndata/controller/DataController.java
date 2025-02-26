package com.example.spring.feigndata.controller;

import com.example.spring.feigndata.dto.DataResponseDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private Map<Long, DataResponseDTO> dataStore = new HashMap<>();
    private Long idCnt = 1L;
    
    // 초기 데이터를 추가하는 메서드
    @PostConstruct
    public void initDataSource() {
        dataStore.put(idCnt++, new DataResponseDTO(1L, "Item 1",100));
        dataStore.put(idCnt++, new DataResponseDTO(1L, "Item 2",200));
        dataStore.put(idCnt++, new DataResponseDTO(1L, "Item 3",300));
        dataStore.put(idCnt++, new DataResponseDTO(1L, "Item 4",400));
        dataStore.put(idCnt++, new DataResponseDTO(1L, "Item 5",500));
    }

    @GetMapping("/{id}")
    public DataResponseDTO get(@PathVariable Long id) {
        return dataStore.get(id);
    }

}
