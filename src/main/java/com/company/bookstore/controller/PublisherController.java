package com.company.bookstore.controller;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController {
    @Autowired
    PublisherRepository publisherRepo;

    @GetMapping("/publishers")
    public List<Publisher> getAllPublishers() {
        return publisherRepo.findAll();
    }

    @GetMapping("/publishers/{publisherId}")
    public Optional<Publisher> getPublisherById(@PathVariable("publisherId") int publisherId) {
        return publisherRepo.findById(publisherId);
    }

    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addPublisher(@RequestBody Publisher publisher) {
        return publisherRepo.save(publisher);
    }

    @PutMapping("/publishers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher(@RequestBody Publisher publisher) {publisherRepo.save(publisher);}

    @DeleteMapping("/publishers/{publisherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable("publisherId") int publisherId) {
        publisherRepo.deleteById(publisherId);
    }
}
