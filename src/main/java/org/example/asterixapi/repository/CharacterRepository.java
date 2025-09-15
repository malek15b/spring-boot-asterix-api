package org.example.asterixapi.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository {

    Character getCharacterByName(String name);
}
