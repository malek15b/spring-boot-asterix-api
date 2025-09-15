package org.example.asterixapi.controller;

import org.example.asterixapi.model.CharacterDto;
import org.example.asterixapi.service.CharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.example.asterixapi.model.Character;

@RestController
@RequestMapping ("/asterix")
public class AsterixController {

    private final CharacterService characterService;

    public AsterixController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/characters")
    public List<Character> getCharacters() {
        return this.characterService.getCharacters();
    }

    @GetMapping("/characters/avg-age")
    public String getAverage(@RequestParam String profession) {
        if(profession == null) {
            return null;
        }
        return this.characterService.getAverage(profession);
    }

    @PostMapping("/character")
    public Character getCharacter(@RequestBody CharacterDto character) {
        return this.characterService.getCharacter(character);
    }

    @GetMapping("/character/{id}")
    public Character getCharacter(@PathVariable String id) {
        return this.characterService.getCharacter(id);
    }

    @DeleteMapping("/character/{id}")
    public void removeCharacter(@PathVariable String id) {
        this.characterService.removeCharacter(id);
    }

}
