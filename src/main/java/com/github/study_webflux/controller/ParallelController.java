package com.github.study_webflux.controller;

import com.github.study_webflux.service.ParallelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ParallelController {

    @Autowired
    private ParallelService parallelService;

    @GetMapping("/process")
    public Flux<String> processItem() {
        return parallelService.processParallel();
    }
}
