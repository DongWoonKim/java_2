package com.example.spring.catalogservice.domain;

import lombok.Builder;

@Builder
public record Book(
        String isbn,
        String author,
        String title,
        Double price
) {}
