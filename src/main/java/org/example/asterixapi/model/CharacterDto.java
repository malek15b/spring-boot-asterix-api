package org.example.asterixapi.model;

import java.util.UUID;

public record CharacterDto(
        String name,
        int age, String
        profession) {

    public Character getCharacter(String id) {
        return new Character(id, name, age, profession);
    }
}
