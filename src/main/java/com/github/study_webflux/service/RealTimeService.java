package com.github.study_webflux.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class RealTimeService {

    public Flux<String> generatedEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(index -> "Event " + index)
                .take(10);
    }
}
