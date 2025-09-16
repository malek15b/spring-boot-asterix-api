package org.example.asterixapi.model;

import java.time.LocalDateTime;

public record Character(
        String id,
        String name,
        int age,
        String profession,
        LocalDateTime createdAt) {
}
