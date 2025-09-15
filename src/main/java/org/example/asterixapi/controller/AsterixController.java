package org.example.asterixapi.controller;

import org.example.asterixapi.repository.CharacterRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.example.asterixapi.model.Character;

@RestController
@RequestMapping ("/asterix")
public class AsterixController {

    private final CharacterRepository characterRepository;

    public AsterixController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @GetMapping("/characters")
    public List<Character> getCharacters(@RequestParam String profession) {
        return this.characterRepository.findAll();
    }

    @GetMapping("/characters/avg-age")
    public String getAverage(@RequestParam String profession) {
        if(profession == null) {
            return null;
        }
        List<Character> characters = characterRepository.findCharactersByProfession(profession);
        Double result = characters.stream().mapToInt(Character::age).average().orElse(0.0);
        return String.format("%.2f", result);
    }

    @PostMapping("/character")
    public Character getCharacter(@RequestBody Character character) {
        return this.characterRepository.save(character);
    }

    @GetMapping("/character/{id}")
    public Character getCharacter(@PathVariable String id) {
        return this.characterRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/character/{id}")
    public void removeCharacter(@PathVariable String id) {
        this.characterRepository.deleteById(id);
    }

}
