package com.github.study_webflux.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DataService {

    public Mono<String> fetchDataAsync() {
        return Mono.fromCallable(() -> {
            Thread.sleep(2000);
            return "data fetched successfully";
        });
    }
}
