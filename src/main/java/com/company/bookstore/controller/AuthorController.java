package com.company.bookstore.controller;


import com.company.bookstore.model.Author;
import com.company.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {
    @Autowired
    AuthorRepository authorRepo;

    @GetMapping("/authors")
    public List<Author> getAllAuthors(){ return  authorRepo.findAll();}

    @GetMapping("/authors/{authorId}")
    public Optional<Author> getAuthorById(@PathVariable("authorId") int authorId){ return authorRepo.findById(authorId); }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@RequestBody Author author) { return authorRepo.save(author); }

    @PutMapping("/authors")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthor(@RequestBody Author author) {authorRepo.save(author);
    }

    @DeleteMapping("/authors/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable("authorId") int authorId){ authorRepo.deleteById(authorId);}

}
