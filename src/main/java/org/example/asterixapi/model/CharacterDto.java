package org.example.asterixapi.model;

import java.util.UUID;

public record CharacterDto(
        String name,
        int age, String
        profession) {

    public Character getCharacter() {
        return new Character(UUID.randomUUID().toString(), name, age, profession);
    }
}
