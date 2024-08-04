package com.eventdriven.publisherservice.service;

import com.eventdriven.publisherservice.exception.PublisherServiceException;
import com.eventdriven.publisherservice.model.PublishRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.eventdriven.common.schema.PublishSchema;

import java.util.List;

import static com.eventdriven.publisherservice.util.QueryUtils.insertSql;
import static com.eventdriven.publisherservice.util.QueryUtils.selectSql;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublisherService {

    private final JdbcTemplate jdbcTemplate;
    private final Producer producer;

    public void publish(final PublishRequest publishRequest) {
        runSafe(() -> {
            validateRequest(publishRequest.isbn());
            jdbcTemplate.update(insertSql(), publishRequest.isbn(), publishRequest.title(),
                    publishRequest.author().name(), publishRequest.author().age());
            producer.send(PublishSchema.newBuilder()
                    .setIsbn(publishRequest.isbn())
                    .setTitle(publishRequest.title())
                    .setAuthorName(publishRequest.author().name())
                    .setAuthorAge(publishRequest.author().age()).build());
        });
    }

    private void validateRequest(final String isbn) {
        final List<PublishRequest> result = jdbcTemplate.query(selectSql(),
                (rs, rowNum) -> new PublishRequest(
                        rs.getString("isbn"),
                        rs.getString("title"),
                        new PublishRequest.AuthorRequest(
                                rs.getString("author_name"),
                                rs.getInt("author_age"))), isbn);
        if (!result.isEmpty()) {
            throw PublisherServiceException.invalidRequest("Request with isbn already exists");
        }
    }

    private void runSafe(final Runnable runnable) {
        try {
            runnable.run();
        } catch (PublisherServiceException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw PublisherServiceException.sqlException(e);
        }
    }
}
