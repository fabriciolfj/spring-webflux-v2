package com.github.study_webflux.controller;

import com.github.study_webflux.entities.Event;
import com.github.study_webflux.service.EventService;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Endpoint para publicar eventos
    @PostMapping("/events")
    public void publishEvent(@RequestBody Event event) {
        eventService.publishEvent(event);
    }

    // Endpoint SSE para consumir eventos
    @GetMapping(value = "/events/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Event>> streamEvents() {
        return eventService.subscribeToEvents()
                .map(event -> ServerSentEvent.<Event>builder()
                        .id(UUID.randomUUID().toString())
                        .event("event-type")
                        .data(event)
                        .build());
    }
}