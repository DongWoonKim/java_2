package com.example.spring.feignclient.controller;

import com.example.spring.feignclient.client.ExampleClient;
import com.example.spring.feignclient.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feign/data")
public class ExampleApiController {

    private final ExampleService exampleService;

    @GetMapping("/{id}")
    public String getData(@PathVariable Long id) {
        System.out.println("[CLIENT] GET IN");
        return exampleService.getDataById(id);
    }

    @PostMapping
    public String createData(@RequestParam String name, @RequestParam int value) {
        return exampleService.createData(name, value);
    }
}
