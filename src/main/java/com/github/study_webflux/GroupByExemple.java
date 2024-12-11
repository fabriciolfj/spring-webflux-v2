package com.github.study_webflux;

import reactor.core.publisher.Flux;

public class GroupByExemple {

    public static void main(String[] args) {
        var numbers = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        var groupedBy = numbers.groupBy(n -> n % 2 == 0 ? "Even" : "Odd");

        groupedBy.subscribe(g -> {
            g.subscribe(number -> System.out.println(g.key()
            + " " + number));
        });
    }
}
