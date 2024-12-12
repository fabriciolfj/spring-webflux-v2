package com.github.study_webflux.controller;

import com.github.study_webflux.service.RealTimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class RealTimeController {

    private final RealTimeService realTimeService;

    public RealTimeController(final RealTimeService realTimeService) {
        this.realTimeService = realTimeService;
    }

    @GetMapping("/api/events")
    public Flux<String> getRealTimeEvents() {
        return realTimeService.generatedEvents();
    }
}
