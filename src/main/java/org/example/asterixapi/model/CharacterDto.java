package org.example.asterixapi.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record CharacterDto(
        String name,
        int age, String
        profession) {

    public Character getCharacter(String id) {
        return new Character(id, name, age, profession, null);
    }

    public Character getCharacter(String id, LocalDateTime createdAt) {
        return new Character(id, name, age, profession, createdAt);
    }
}
