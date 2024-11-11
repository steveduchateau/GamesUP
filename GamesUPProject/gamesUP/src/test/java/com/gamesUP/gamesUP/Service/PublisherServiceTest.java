package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Publisher;
import com.gamesUP.gamesUP.Repository.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import java.util.List;

public class PublisherServiceTest {

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherService publisherService;

    private Publisher publisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
        publisher = new Publisher();
        publisher.setId(1);
        publisher.setName("Publisher 1");
    }

    @Test
    void testGetAllPublishers() {
        // Correct usage of matchers in the 'when' method
        Mockito.when(publisherRepository.findAll()).thenReturn(List.of(publisher));

        List<Publisher> result = publisherService.getAllPublishers();
        assertEquals(1, result.size());
        assertEquals("Publisher 1", result.get(0).getName());
    }

    @Test
    void testGetPublisherByIdFound() {
        // Correct usage of matchers in the 'when' method
        Mockito.when(publisherRepository.findById(1)).thenReturn(Optional.of(publisher));

        Optional<Publisher> result = publisherService.getPublisherById(1);
        assertTrue(result.isPresent());
        assertEquals("Publisher 1", result.get().getName());
    }

    @Test
    void testGetPublisherByIdNotFound() {
        // Correct usage of matchers in the 'when' method
        Mockito.when(publisherRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Publisher> result = publisherService.getPublisherById(1);
        assertFalse(result.isPresent());
    }

    @Test
    void testAddPublisher() {
        // Correct usage of matchers in the 'when' method
        Mockito.when(publisherRepository.save(Mockito.any(Publisher.class))).thenReturn(publisher);

        Publisher result = publisherService.addPublisher(publisher);
        assertEquals("Publisher 1", result.getName());
    }

    @Test
    void testUpdatePublisherFound() {
        // Correct usage of matchers in the 'when' method
        Mockito.when(publisherRepository.findById(1)).thenReturn(Optional.of(publisher));
        Mockito.when(publisherRepository.save(Mockito.any(Publisher.class))).thenReturn(publisher);

        Publisher updatedPublisher = publisherService.updatePublisher(1, publisher);
        assertEquals("Publisher 1", updatedPublisher.getName());
    }

    @Test
    void testUpdatePublisherNotFound() {
        // Correct usage of matchers in the 'when' method
        Mockito.when(publisherRepository.findById(1)).thenReturn(Optional.empty());

        Publisher updatedPublisher = publisherService.updatePublisher(1, publisher);
        assertNull(updatedPublisher);
    }

    @Test
    void testDeletePublisher() {
        // Correct usage of matchers in the 'doNothing' method
        Mockito.doNothing().when(publisherRepository).deleteById(1);

        publisherService.deletePublisher(1);

        // Verify if the delete method was called
        Mockito.verify(publisherRepository).deleteById(1);
    }
}
