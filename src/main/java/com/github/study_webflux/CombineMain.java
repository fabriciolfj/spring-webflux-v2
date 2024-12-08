package com.github.study_webflux;

import reactor.core.publisher.Flux;

public class CombineMain {

    public static void main(String[] args) {
        var numbers = Flux.just(1, 3, 2);
        var numbers2 = Flux.just(10, 20, 30);

        var combined = Flux.combineLatest(numbers, numbers2,
                (n1, n2)
                -> n1 + n2);

        combined.subscribe(System.out::println);
    }
}
