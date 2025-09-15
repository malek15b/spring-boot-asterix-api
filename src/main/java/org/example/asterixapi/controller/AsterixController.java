package org.example.asterixapi.controller;

import jakarta.websocket.server.PathParam;
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
    public List<Character> getCharacters(@RequestParam int age) {
        if(age != 0) {
            return this.characterService.getCharacters(age);
        }
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
    public Character addCharacter(@RequestBody CharacterDto character) {
        return this.characterService.addCharacter(character);
    }

    @PostMapping("/character/{id}")
    public Character updateCharacter(@PathVariable String id,
                                     @RequestBody CharacterDto characterDto) {
        Character character = characterDto.getCharacter(id);
        return this.characterService.updateCharacter(character);
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
