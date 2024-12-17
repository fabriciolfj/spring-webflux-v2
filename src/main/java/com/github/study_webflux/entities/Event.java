package com.github.study_webflux.entities;

import java.time.LocalDateTime;

public record Event(String type, String data, LocalDateTime timestamp) {
    public Event(String type, String data) {
        this(type, data, LocalDateTime.now());
    }
}