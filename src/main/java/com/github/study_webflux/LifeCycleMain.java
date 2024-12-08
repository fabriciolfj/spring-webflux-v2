package com.github.study_webflux;

import reactor.core.publisher.Mono;

public class LifeCycleMain {

    public static void main(String[] args) {
        Mono.just(10)
                .doOnSubscribe(v ->  System.out.println("subscribe " + v))
                .doOnSuccess(v ->  System.out.println("success " + v))
                .doOnError(v ->  System.out.println("error " + v))
                .subscribe();

    }
}
