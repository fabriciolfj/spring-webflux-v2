package com.github.study_webflux.service;

import com.github.study_webflux.entities.Event;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class EventService {
    // Many é bom para múltiplos subscribers, unicast para um único
    private final Sinks.Many<Event> eventSink = Sinks.many().multicast().onBackpressureBuffer();

    // Flux que os clientes podem se inscrever
    private final Flux<Event> events = eventSink.asFlux();

    public void publishEvent(Event event) {
        eventSink.tryEmitNext(event)
                .orThrow(); // ou use emitNext() se preferir lançar exceção diretamente
    }

    public Flux<Event> subscribeToEvents() {
        return events;
    }
}
