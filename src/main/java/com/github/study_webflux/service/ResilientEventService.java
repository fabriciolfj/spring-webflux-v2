package com.github.study_webflux.service;

import com.github.study_webflux.entities.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

// Exemplo de service com retry
@Service
public class ResilientEventService {
    private final Sinks.Many<Event> eventSink;
    private final Logger logger = LoggerFactory.getLogger(ResilientEventService.class);

    public ResilientEventService(@Qualifier("replaySink") Sinks.Many<Event> eventSink) {
        this.eventSink = eventSink;
    }

    public void publishEventWithRetry(Event event) {
        Sinks.EmitResult result = eventSink.tryEmitNext(event);

        if (result != Sinks.EmitResult.OK) {
            // Retry logic
            for (int i = 0; i < 3 && result != Sinks.EmitResult.OK; i++) {
                logger.warn("Retry attempt {} for event emission", i + 1);
                try {
                    Thread.sleep(100 * (i + 1)); // Exponential backoff
                    result = eventSink.tryEmitNext(event);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted during retry", e);
                }
            }

            if (result != Sinks.EmitResult.OK) {
                throw new RuntimeException("Failed to emit event after retries: " + result);
            }
        }
    }
}
