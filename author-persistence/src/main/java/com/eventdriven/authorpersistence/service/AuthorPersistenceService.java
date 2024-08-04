package com.eventdriven.authorpersistence.service;

import com.eventdriven.authorpersistence.model.Author;
import com.eventdriven.common.schema.AuthorSchema;
import com.eventdriven.common.schema.PublishSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorPersistenceService {

    private final AuthorPersistenceMapper mapper;
    private final JdbcTemplate jdbcTemplate;
    private final Producer producer;

    public void persist(final PublishSchema publishSchema) {
        final String sql = "INSERT INTO authors (id, name, age) VALUES (?, ?, ?)";
        final Author author = mapper.fromSchema(publishSchema);
        jdbcTemplate.update(sql, author.getId(), author.getName(), author.getAge());
        final AuthorSchema authorSchema = mapper.fromAuthor(author);
        producer.send(authorSchema);
    }
}
