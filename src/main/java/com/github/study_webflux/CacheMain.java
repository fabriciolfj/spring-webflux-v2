package com.github.study_webflux;

import reactor.core.publisher.Flux;

public class CacheMain {

    public static void main(String[] args) {
        var numbers =  Flux.range(1, 10)
                .cache();

        numbers.subscribe(System.out::println);
        System.out.println("==========");
        numbers.subscribe(System.out::println);
    }
}
