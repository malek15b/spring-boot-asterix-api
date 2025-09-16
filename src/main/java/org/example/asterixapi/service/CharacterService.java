package org.example.asterixapi.service;

import lombok.RequiredArgsConstructor;
import org.example.asterixapi.event.CustomSpringEvent;
import org.example.asterixapi.model.Character;
import org.example.asterixapi.model.CharacterDto;
import org.example.asterixapi.repository.CharacterRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final IdService idService;
    private final ApplicationEventPublisher events;

    public List<Character> getCharacters() {
        return this.characterRepository.findAll();
    }

    public List<Character> getCharacters(int age) {
        System.out.println("Publishing custom event. ");
        CustomSpringEvent customSpringEvent = new CustomSpringEvent("Avg: " + age);
        events.publishEvent(customSpringEvent);
        return this.characterRepository.findCharactersByQuery(age);
    }

    public String getAverage(String profession) {
        List<Character> characters = characterRepository.findCharactersByProfession(profession);
        Double result = characters.stream()
                .mapToInt(Character::age)
                .average()
                .orElse(0.0);
        return String.format("%.2f", result);
    }

    public Character addCharacter(CharacterDto characterDto) {
        Character character = characterDto.getCharacter(idService.randomId());
        this.characterRepository.save(character);
        return character;
    }

    public Character updateCharacter(Character character) {
        return this.characterRepository.save(character);
    }

    public Character getCharacter(String id) {
        return this.characterRepository.findById(id).orElse(null);
    }

    public void removeCharacter(String id) {
        this.characterRepository.deleteById(id);
    }
}
