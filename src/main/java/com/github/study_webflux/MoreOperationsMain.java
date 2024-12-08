package com.github.study_webflux;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class MoreOperationsMain {

    public static void main(String[] args) throws InterruptedException {
        var numbers = Flux.range(1, 10)
                .limitRate(5)
                .delayElements(Duration.ofMillis(500))
                .publish()//compartilhar com mais subscribes
                .autoConnect();

        numbers.subscribe(v -> System.out.println("subs1 " + v));
        numbers.subscribe(v -> System.out.println("subs2 " + v));

        Thread.sleep(100000);
    }
}
