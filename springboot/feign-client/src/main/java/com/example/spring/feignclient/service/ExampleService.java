package com.example.spring.feignclient.service;

import com.example.spring.feignclient.client.ExampleClient;
import com.example.spring.feignclient.dto.DataRequestDTO;
import com.example.spring.feignclient.dto.DataResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleClient exampleClient;

    // GET 요청 호출
    public String getDataById(Long id) {
        return exampleClient.getData(id);
    }

    public String createData(String name, int value) {
        return exampleClient.createData(
                DataRequestDTO.builder()
                        .name(name)
                        .value(value)
                        .build()
        );
    }

    public String updateDataById(Long id, String name, int value) {
        return exampleClient.updateData(
                id,
                DataRequestDTO.builder()
                        .name(name)
                        .value(value)
                        .build()
        );
    }

    public String deleteDataById(Long id) {
        return exampleClient.deleteData(id);
    }

    public List<DataResponseDTO> getAllData() {
        return exampleClient.getAllData();
    }
}
