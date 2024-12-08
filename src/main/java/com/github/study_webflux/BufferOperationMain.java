package com.github.study_webflux;

import reactor.core.publisher.Flux;

public class BufferOperationMain {

    public static void main(String[] args) {
        Flux<Integer> numbers = Flux.range(1, 10);
        var buffered = numbers.buffer(3);

        buffered.subscribe(System.out::println);
    }
}
