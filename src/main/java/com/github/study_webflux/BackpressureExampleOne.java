package com.github.study_webflux;

import reactor.core.publisher.Flux;

public class BackpressureExampleOne {

    public static void main(String[] args) {
        var numbers = Flux.range(1, 100);

        numbers
                .doOnRequest(request -> System.out.println("requested: " + request))
                .subscribe(value -> {
                            System.out.println("received: " + value);
                            try {
                                Thread.sleep(2000);
                                ;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        },
                        throwable -> System.out.println("error: " + throwable),
                        () -> System.out.println("complete"));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
