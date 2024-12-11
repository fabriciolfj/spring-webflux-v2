package com.github.study_webflux;

import reactor.core.publisher.Flux;

import java.util.Arrays;

public class FlaMapIterableExample {

    public static void main(String[] args) {
        var words = Flux.just("hello", "reactor", "world");

        var letters = words.flatMapIterable(w -> Arrays.asList(w.toCharArray()));

        letters.subscribe(System.out::println);
    }
}
