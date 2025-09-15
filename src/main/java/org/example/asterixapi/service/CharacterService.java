package org.example.asterixapi.service;

import org.example.asterixapi.model.Character;
import org.example.asterixapi.model.CharacterDto;
import org.example.asterixapi.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final IdService idService;

    public CharacterService(
            CharacterRepository characterRepository,
            IdService idService) {
        this.characterRepository = characterRepository;
        this.idService = idService;
    }

    public List<Character> getCharacters() {
        return this.characterRepository.findAll();
    }

    public String getAverage(String profession) {
        List<Character> characters = characterRepository.findCharactersByProfession(profession);
        Double result = characters.stream()
                .mapToInt(Character::age)
                .average()
                .orElse(0.0);
        return String.format("%.2f", result);
    }

    public Character getCharacter(CharacterDto characterDto) {
        Character character = characterDto.getCharacter(idService.randomId());
        return this.characterRepository.save(character);
    }

    public Character getCharacter(String id) {
        return this.characterRepository.findById(id).orElse(null);
    }

    public void removeCharacter(String id) {
        this.characterRepository.deleteById(id);
    }
}
