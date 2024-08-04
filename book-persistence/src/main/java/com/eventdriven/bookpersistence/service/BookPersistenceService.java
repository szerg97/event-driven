package com.eventdriven.bookpersistence.service;

import com.eventdriven.bookpersistence.model.Book;
import com.eventdriven.common.schema.BookSchema;
import com.eventdriven.common.schema.PublishSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookPersistenceService {

    private final BookPersistenceMapper mapper;
    private final JdbcTemplate jdbcTemplate;
    private final Producer producer;

    public void persist(final PublishSchema publishSchema) {
        final String sql = "INSERT INTO books (isbn, title) VALUES (?, ?)";
        final Book book = mapper.fromSchema(publishSchema);
        jdbcTemplate.update(sql, book.getIsbn(), book.getTitle());
        final BookSchema bookSchema = mapper.fromAuthor(book);
        producer.send(bookSchema);
    }
}
