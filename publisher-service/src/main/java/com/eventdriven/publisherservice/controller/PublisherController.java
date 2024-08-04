package com.eventdriven.publisherservice.controller;

import com.eventdriven.publisherservice.model.PublishRequest;
import com.eventdriven.publisherservice.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping("/publish")
    public ResponseEntity<Void> publish(@RequestBody PublishRequest publishRequest) {
        publisherService.publish(publishRequest);
        return ResponseEntity.ok().build();
    }
}
