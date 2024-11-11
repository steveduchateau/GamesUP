package com.gamesUP.gamesUP.Controller;

import com.gamesUP.gamesUP.model.Publisher;
import com.gamesUP.gamesUP.Service.PublisherService;
import com.gamesUP.gamesUP.controller.PublisherController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PublisherControllerTest {

    @Mock
    private PublisherService publisherService;

    @InjectMocks
    private PublisherController publisherController;

    private Publisher publisher;

    @BeforeEach
    void setUp() {
        publisher = new Publisher();
        publisher.setId(1);
        publisher.setName("Publisher 1");
    }

    @Test
    void testGetAllPublishers() {
        Mockito.when(publisherService.getAllPublishers()).thenReturn(List.of(publisher));
        var response = publisherController.getAllPublishers();
        assertEquals(1, response.size());
        assertEquals("Publisher 1", response.get(0).getName());
    }

    @SuppressWarnings("null")
    @Test
    void testGetPublisherByIdFound() {
        Mockito.when(publisherService.getPublisherById(1)).thenReturn(Optional.of(publisher));
        ResponseEntity<Publisher> response = publisherController.getPublisherById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Publisher 1", response.getBody().getName());
    }

    @Test
    void testGetPublisherByIdNotFound() {
        Mockito.when(publisherService.getPublisherById(1)).thenReturn(Optional.empty());
        ResponseEntity<Publisher> response = publisherController.getPublisherById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @SuppressWarnings("null")
    @Test
    void testAddPublisher() {
        Mockito.when(publisherService.addPublisher(Mockito.any(Publisher.class))).thenReturn(publisher);
        ResponseEntity<Publisher> response = publisherController.addPublisher(publisher);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Publisher 1", response.getBody().getName());
    }

    @SuppressWarnings("null")
    @Test
    void testUpdatePublisherFound() {
        Mockito.when(publisherService.updatePublisher(1, publisher)).thenReturn(publisher);
        ResponseEntity<Publisher> response = publisherController.updatePublisher(1, publisher);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Publisher 1", response.getBody().getName());
    }

    @Test
    void testUpdatePublisherNotFound() {
        Mockito.when(publisherService.updatePublisher(1, publisher)).thenReturn(null);
        ResponseEntity<Publisher> response = publisherController.updatePublisher(1, publisher);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeletePublisher() {
        Mockito.doNothing().when(publisherService).deletePublisher(1);
        ResponseEntity<Void> response = publisherController.deletePublisher(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
