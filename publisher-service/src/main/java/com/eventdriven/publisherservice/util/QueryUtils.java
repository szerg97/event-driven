package com.eventdriven.publisherservice.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class QueryUtils {

    public static String insertSql() {
        return """
                INSERT INTO publish_request (isbn, title, author_name, author_age) VALUES (?, ?, ?, ?)
                """;
    }

    public static String selectSql() {
        return """
                SELECT isbn, title, author_name, author_age FROM publish_request WHERE isbn = ?
                """;
    }
}
