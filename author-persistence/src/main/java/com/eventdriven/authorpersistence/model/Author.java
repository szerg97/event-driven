package com.eventdriven.authorpersistence.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Author {
    private final String id = UUID.randomUUID().toString();
    private final String name;
    private final int age;
}
