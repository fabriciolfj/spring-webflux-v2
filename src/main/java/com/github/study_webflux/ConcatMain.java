package com.github.study_webflux;

import reactor.core.publisher.Flux;

//garante a ordem que sem emitidos
public class ConcatMain {

    public static void main(String[] args) {
        var numbers1 = Flux.just(1,2, 3);
        var numbers2 = Flux.just(4, 5, 6);

        var concatenated = Flux.concat(numbers1, numbers2);
        concatenated.subscribe(System.out::println);
    }
}
