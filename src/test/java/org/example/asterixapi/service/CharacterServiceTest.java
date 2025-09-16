package org.example.asterixapi.service;

import org.example.asterixapi.model.CharacterDto;
import org.example.asterixapi.repository.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.example.asterixapi.model.Character;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class CharacterServiceTest {

    @Test
    void addCharacter_shouldReturnCharacterWithId() {
        //GIVEN
        CharacterRepository characterRepository = mock(CharacterRepository.class);
        IdService idService =  mock(IdService.class);
        String id = UUID.randomUUID().toString();
        Mockito.when(idService.randomId()).thenReturn(id);

        CharacterService service = new CharacterService(
                characterRepository,
                idService,
                null);

        CharacterDto characterDto = new CharacterDto("Max", 25, "CEO");
        //WHEN
        Character character = service.addCharacter(characterDto);
        //THEN
        assertEquals(id, character.id());
    }
}