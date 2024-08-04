package com.eventdriven.bookpersistence.service;

import com.eventdriven.bookpersistence.model.Book;
import com.eventdriven.common.schema.BookSchema;
import com.eventdriven.common.schema.PublishSchema;
import org.springframework.stereotype.Service;

@Service
public class BookPersistenceMapper {

    public Book fromSchema(final PublishSchema schema) {
        return new Book(schema.getIsbn().toString(), schema.getTitle().toString());
    }

    public BookSchema fromAuthor(final Book book) {
        return BookSchema.newBuilder()
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle()).build();
    }
}
