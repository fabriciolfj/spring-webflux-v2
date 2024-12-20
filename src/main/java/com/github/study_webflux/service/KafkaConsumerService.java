package com.github.study_webflux.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.UnicastProcessor;

@Service
public class KafkaConsumerService {

    private final Sinks.Many<String> sink;
    private final Flux<String> flux;

    public KafkaConsumerService() {
        this.sink = Sinks.many().unicast().onBackpressureBuffer();
        this.flux = sink.asFlux();
    }

    @KafkaListener(topics = "webflux-topic", groupId = "webflux-group")
    public void consume(String message) {
        sink.tryEmitNext(message);
    }

    public Flux<String> subscribe() {
        return flux.publish().autoConnect();
    }
}
