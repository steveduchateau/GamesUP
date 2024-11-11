package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.Repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    public void testFindAuthorById() {
        Author author = new Author("J.K. Rowling");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Optional<Author> foundAuthor = authorService.getAuthorById(1L);
        assertTrue(foundAuthor.isPresent());
        assertEquals("J.K. Rowling", foundAuthor.get().getName());
    }

    @Test
    public void testSaveAuthor() {
        Author author = new Author("George R.R. Martin");
        when(authorRepository.save(author)).thenReturn(author);

        Author savedAuthor = authorService.addAuthor(author);
        assertNotNull(savedAuthor);
        assertEquals("George R.R. Martin", savedAuthor.getName());
    }
}
