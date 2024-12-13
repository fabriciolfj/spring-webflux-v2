package com.github.study_webflux.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class ConcurrencyService {

    public Flux<Integer> processConcurrently() {
        var numbers = Flux.range(1, 10);

        return numbers
                .parallel()
                .runOn(Schedulers.parallel())
                .map(this::processNumber)
                .sequential();
    }

    private Integer processNumber(final Integer number) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return number * 2;
    }
}
