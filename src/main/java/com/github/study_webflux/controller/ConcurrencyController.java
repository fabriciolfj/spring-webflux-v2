package com.github.study_webflux.controller;

import com.github.study_webflux.service.ConcurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ConcurrencyController {

    @Autowired
    private ConcurrencyService concurrencyService;

    @GetMapping("/process-concurrently")
    public Flux<Integer> processConcurrently() {
        return concurrencyService.processConcurrently();
    }
}
