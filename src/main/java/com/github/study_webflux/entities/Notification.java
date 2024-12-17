package com.github.study_webflux.entities;

import java.time.Instant;
import java.util.UUID;

public class Notification {

    private String id;
    private Instant instant;

    public Notification() {
        this.id = UUID.randomUUID().toString();
        this.instant = Instant.now();
    }

    public Instant getInstant() {
        return instant;
    }

    public String getId() {
        return id;
    }
}
