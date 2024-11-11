package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Publisher;
import com.gamesUP.gamesUP.Repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    // Méthode pour récupérer tous les éditeurs
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    // Méthode pour récupérer un éditeur par son ID
    public Optional<Publisher> getPublisherById(int id) {
        return publisherRepository.findById(id);
    }

    // Méthode pour ajouter un nouvel éditeur
    public Publisher addPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    // Méthode pour mettre à jour un éditeur
    public Publisher updatePublisher(int id, Publisher publisherDetails) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (publisherOptional.isPresent()) {
            Publisher publisher = publisherOptional.get();
            publisher.setName(publisherDetails.getName());
            return publisherRepository.save(publisher);
        }
        return null;
    }

    // Méthode pour supprimer un éditeur
    public void deletePublisher(int id) {
        publisherRepository.deleteById(id);
    }
}
