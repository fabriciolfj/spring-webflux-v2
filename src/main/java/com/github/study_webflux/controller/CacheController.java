package com.github.study_webflux.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private final WebClient webClient;
    private final Cache<String, Mono<String>> responseCache;

    public CacheController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
        this.responseCache = Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(5))
                .maximumSize(100)
                .build();
    }

    @GetMapping
    public Mono<String> fetchData() {
        return responseCache.get("data", key -> webClient.get()
                .uri("/todos")
                .retrieve()
                .bodyToMono(String.class));
    }
}
