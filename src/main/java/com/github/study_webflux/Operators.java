package com.github.study_webflux;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

public class Operators {

    public static void main(String[] args) {
        var numbers = Flux.just(1, 2, 3, 4, 5);
        var doubleNumbers = numbers.map(v -> v * 2);

        final Flux<Tuple2<Integer, Integer>> zipped = numbers
                .zipWith(numbers.map(v -> v * v));

        var merge = Flux.merge(numbers, doubleNumbers);

        zipped.subscribe(System.out::println);
        System.out.println("=============");
        merge.subscribe(System.out::println);

        System.out.println("=============");

        numbers = Flux.just(1, 2, 3);
        Flux<Integer> expanded = numbers.expand(n ->
                Flux.just(n * 2)
                        .takeWhile(x -> x < 10));

        expanded.subscribe(System.out::println);
    }
}
