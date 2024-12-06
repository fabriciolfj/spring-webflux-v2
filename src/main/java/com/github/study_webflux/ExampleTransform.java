package com.github.study_webflux;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.function.Function;

public class ExampleTransform {

    public static void main(String[] args) {
        Function<Flux<String>, Flux<String>> filterMap = f -> f.filter(
                name -> !name.equals("Author")).map(String::toUpperCase);

        Flux.fromIterable(Arrays.asList("Author", "book", "publisher", "isbn"))
                .doOnNext(System.out::println)
                .transform(filterMap)
                .subscribe(
                        d -> System.out.println("Subscribed to transformed stream: " + d)
                );
    }
}
