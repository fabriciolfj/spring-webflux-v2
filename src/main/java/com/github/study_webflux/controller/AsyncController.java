package com.github.study_webflux.controller;

import com.github.study_webflux.service.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AsyncController {

    private final DataService dataService;

    public AsyncController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/api/data")
    public Mono<String> fetchData() {
        return dataService.fetchDataAsync();
    }
}
