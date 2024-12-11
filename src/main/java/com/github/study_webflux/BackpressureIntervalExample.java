package com.github.study_webflux;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class BackpressureIntervalExample {

    public static void main(String[] args) {
        Flux<Long> intervalNumbers = Flux.interval(Duration.ofSeconds(1));

        intervalNumbers
                .doOnRequest(requested ->  System.out.println("requested: " + requested))
                .subscribe(value -> {
                    System.out.println("received: " + value);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, error -> System.out.println("error: " + error),
                        () -> System.out.println("complete"));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
