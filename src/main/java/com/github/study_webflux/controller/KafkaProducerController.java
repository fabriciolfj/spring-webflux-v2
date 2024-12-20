package com.github.study_webflux.controller;

import com.github.study_webflux.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
public class KafkaProducerController {

    private final KafkaProducerService kafkaProducerService;

    @GetMapping
    public String sendMessage(@RequestParam("message") final String message) {
        kafkaProducerService.sendMessage(message);
        return "Message sent: " + message;
    }
}
