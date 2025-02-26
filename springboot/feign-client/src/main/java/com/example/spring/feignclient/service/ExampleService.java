package com.example.spring.feignclient.service;

import com.example.spring.feignclient.client.ExampleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleClient exampleClient;

    // GET 요청 호출
    public String getDataById(Long id) {
        return exampleClient.getData(id);
    }

}
