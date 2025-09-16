package org.example.asterixapi.controller;

import org.example.asterixapi.AsterixApiApplication;
import org.example.asterixapi.model.CharacterDto;
import org.example.asterixapi.model.Character;
import org.example.asterixapi.repository.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = AsterixApiApplication.class)
@AutoConfigureMockMvc
class AsterixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CharacterRepository characterRepository;

    @BeforeEach
    void setup() {
        characterRepository.deleteAll();
    }

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
                                "profession": "CEO"
                                }
                                ]
                                """
                ));
    }

    @Test
    public void addCharacter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/asterix/character")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                           "id": "525",
                           "name": "Malek",
                           "age": 43,
                           "profession": "Senior"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                "id": "525",
                                "name": "Malek",
                                "age": 43,
                                "profession": "Senior"
                                }
                                """
                ));
    }

    @Test
    public void addCharacterDto() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/asterix/characterDto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                           "name": "Malek",
                           "age": 55,
                           "profession": "Senior Java"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                "name": "Malek",
                                "age": 55,
                                "profession": "Senior Java"
                                }
                                """
                ))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").isNotEmpty());
    }
}