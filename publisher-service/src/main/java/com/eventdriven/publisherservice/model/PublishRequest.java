package com.eventdriven.publisherservice.model;

public record PublishRequest(String isbn, String title, AuthorRequest author) {

    public record AuthorRequest(String name, int age) {}
}
