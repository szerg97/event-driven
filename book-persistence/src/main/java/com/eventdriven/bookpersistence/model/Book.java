package com.eventdriven.bookpersistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private final String isbn;
    private final String title;
}
