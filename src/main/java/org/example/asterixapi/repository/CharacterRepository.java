package org.example.asterixapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.example.asterixapi.model.Character;

import java.util.List;

@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {

    Character findCharactersByName(String name);

    List<Character> findCharactersByProfession(String profession);

    List<Character> findCharactersByAgeIsGreaterThan(int age);

    @Query("{ 'age' : { '$gt' : ?0 }}")
    List<Character> findCharactersByQuery(int age);
}
