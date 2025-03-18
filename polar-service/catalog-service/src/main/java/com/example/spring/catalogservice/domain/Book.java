package com.example.spring.catalogservice.domain;

import lombok.Builder;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;

import java.time.Instant;

@Builder
public record Book(
        @Id
        Long id,
        String isbn,
        String author,
        String title,
        Double price,
        @Column("create_at")
        @CreatedDate
        Instant createAt,
        @Column("last_modified_at")
        @LastModifiedDate
        Instant lastModifiedAt,
        @Version
        int version
) {}
