package com.gamesUP.gamesUP.Model;

import org.junit.jupiter.api.Test;

import com.gamesUP.gamesUP.model.Publisher;

import static org.junit.jupiter.api.Assertions.*;

public class PublisherTest {

    @Test
    void testGettersAndSetters() {
        Publisher publisher = new Publisher();
        publisher.setId(1);
        publisher.setName("Publisher 1");

        assertEquals(1, publisher.getId());
        assertEquals("Publisher 1", publisher.getName());
    }

    @Test
    void testToString() {
        Publisher publisher = new Publisher();
        publisher.setId(1);
        publisher.setName("Publisher 1");

        String expected = "Publisher{id=1, name='Publisher 1'}";
        assertEquals(expected, publisher.toString());
    }
}
