package com.github.study_webflux.config;

import com.github.study_webflux.entities.Event;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class SinksConfig {

    @Bean
    public Sinks.Many<Event> multicastSink() {
        // Multicast - múltiplos subscribers
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Sinks.Many<Event> replaySink() {
        // Replay - mantém histórico para novos subscribers
        return Sinks.many().replay().all();
    }

    @Bean
    public Sinks.One<Event> unicastSink() {
        // Unicast - apenas um subscriber
        return Sinks.one();
    }

    @Bean
    public Sinks.Many<Event> directAllOrNothingSink() {
        // Direct com estratégia ALL_OR_NOTHING
        return Sinks.many().multicast().directAllOrNothing();
    }
}
