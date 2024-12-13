package com.github.study_webflux.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ParallelService {

    public Flux<String> processParallel() {
        var items = Flux.just("Item 1", "Item 2", "Item 3", "Item 4", "Item 5");

        return items
                .parallel()
                .runOn(reactor.core.scheduler.Schedulers.parallel())
                .map(this::processItem)
                .sequential();
    }

    private String processItem(String item) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "Processed item: " + item;
    }
}
