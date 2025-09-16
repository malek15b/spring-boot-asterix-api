package org.example.asterixapi.service;

import org.example.asterixapi.model.CharacterDto;
import org.example.asterixapi.repository.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.example.asterixapi.model.Character;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {

    @Captor
    ArgumentCaptor<Character> characterCaptor;

    @Test
    void addCharacter_shouldReturnCharacterWithId() {
        //GIVEN
        CharacterRepository characterRepository = mock(CharacterRepository.class);
        IdService idService =  mock(IdService.class);
        String id = UUID.randomUUID().toString();
        Mockito.when(idService.randomId()).thenReturn(id);

        LocalDate date = LocalDate.of(2020, 1, 1);
        CharacterService service = new CharacterService(
                characterRepository,
                idService,
                null);

        CharacterDto characterDto = new CharacterDto("Max", 25, "CEO");
        LocalDateTime createdAt = LocalDateTime.now();
        //WHEN
        Character character = service.addCharacter(characterDto, createdAt);
        //THEN
        Mockito.verify(characterRepository).save(characterCaptor.capture());

        Mockito.verify(idService).randomId();

        LocalDateTime createdAtCaptor = characterCaptor.getValue().createdAt();
        assertEquals(createdAtCaptor, createdAt);
        assertEquals(id, character.id());
        assertTrue(createdAtCaptor.toLocalDate().isAfter(date));
    }
}