package com.github.study_webflux.controller;

import com.github.study_webflux.service.KafkaConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaConsumerService kafkaConsumerService;

    @GetMapping(value = "/stream-messages", produces = "text/event-stream")
    public Flux<String> streamMessages() {
        return kafkaConsumerService.subscribe();
    }
}
