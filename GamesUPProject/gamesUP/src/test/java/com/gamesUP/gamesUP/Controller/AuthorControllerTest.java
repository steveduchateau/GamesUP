package com.gamesUP.gamesUP.Controller;

import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.Service.AuthorService;
import com.gamesUP.gamesUP.controller.AuthorController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    private MockMvc mockMvc;

    @Test
    public void testGetAuthor() throws Exception {
        // Arrange
        Author author = new Author("J.K. Rowling");
        when(authorService.getAuthorById(1L)).thenReturn(Optional.of(author));

        // Setup MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();

        // Act and Assert
        mockMvc.perform(get("/api/authors/1"))  // Modifié ici pour ajouter le préfixe /api
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("J.K. Rowling"));
    }
}
