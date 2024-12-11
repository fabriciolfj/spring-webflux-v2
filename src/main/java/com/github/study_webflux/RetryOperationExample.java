package com.github.study_webflux;

import reactor.core.publisher.Mono;

public class RetryOperationExample {

    static int count = 1;

    public static void main(String[] args) {
        int maxRetries = 3;

        var result = Mono.fromCallable(() ->  {
           System.out.println("retry count " + count);
           count++;
           if (Math.random() < 0.5) {
               throw new RuntimeException("error");
           }
           return 42;
        }).retry(maxRetries);

        result.subscribe(
                v -> System.out.println("Result: " + v),
                e -> System.out.println("Error: " + e.getMessage())
        );
    }
}
