package com.gamesUP.gamesUP.Model;

import org.junit.jupiter.api.Test;

import com.gamesUP.gamesUP.model.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorTest {

    @Test
    public void testAuthor() {
        Author author = new Author("J.K. Rowling");
        assertEquals("J.K. Rowling", author.getName());
    }
}
