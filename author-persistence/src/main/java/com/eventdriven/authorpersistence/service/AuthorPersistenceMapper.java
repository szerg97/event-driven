package com.eventdriven.authorpersistence.service;

import com.eventdriven.authorpersistence.model.Author;
import com.eventdriven.common.schema.AuthorSchema;
import com.eventdriven.common.schema.PublishSchema;
import org.springframework.stereotype.Service;

@Service
public class AuthorPersistenceMapper {

    public Author fromSchema(final PublishSchema schema) {
        return new Author(schema.getAuthorName().toString(), schema.getAuthorAge());
    }

    public AuthorSchema fromAuthor(final Author author) {
        return AuthorSchema.newBuilder()
                .setId(author.getId())
                .setName(author.getName())
                .setAge(author.getAge()).build();
    }
}
