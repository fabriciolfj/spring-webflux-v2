package com.github.study_webflux.service;

import com.github.study_webflux.entities.Notification;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class NotificationService {

    public Flux<Notification> getEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(v -> new Notification());
    }

}
