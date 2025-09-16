package org.example.asterixapi.controller;

import org.example.asterixapi.AsterixApiApplication;
import org.example.asterixapi.model.CharacterDto;
import org.example.asterixapi.model.Character;
import org.example.asterixapi.repository.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AsterixApiApplication.class)
@AutoConfigureMockMvc
class AsterixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CharacterRepository characterRepository;

    @Test
    public void getCharacters() throws Exception {
        //GIVEN
        CharacterDto characterDto = new CharacterDto("Max", 25, "CEO");
        Character character = characterDto.getCharacter("120");
        characterRepository.save(character);
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters"))
        //THEN
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(
                """
                        [
                        {
                        "id": "120",
                        "name": "Max",
                        "age": 25,
                        "profession": "CEO",
                        "createdAt": null
                        }
                        ]
                        """
        ));
    }
}