package com.example.spring.feignclient.client;

import com.example.spring.feignclient.dto.DataRequestDTO;
import com.example.spring.feignclient.dto.DataResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "exampleClient", url = "${feign-data.url}")
public interface ExampleClient {

    // GET 요청 (데이터 조회)
    @GetMapping("/api/data/{id}")
    String getData(@PathVariable("id") Long id);

    // POST 요청 (데이터 생성)
    @PostMapping("/api/data")
    String createData(@RequestBody DataRequestDTO dataRequestDTO);

    @PutMapping("/api/data/{id}")
    String updateData(@PathVariable("id") Long id, @RequestBody DataRequestDTO dataRequestDTO);

    @DeleteMapping("/api/data/{id}")
    String deleteData(@PathVariable("id") Long id);

    @GetMapping("/api/data/all")
    List<DataResponseDTO> getAllData();
}
