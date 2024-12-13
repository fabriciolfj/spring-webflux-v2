package com.github.study_webflux.controller;

import com.github.study_webflux.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class NetworkController {

    @Autowired
    private NetworkService networkService;

    @GetMapping("/fetch-data")
    public Mono<String> fetchData() {
        return networkService.fetchDataFromExternalApi();
    }
}
