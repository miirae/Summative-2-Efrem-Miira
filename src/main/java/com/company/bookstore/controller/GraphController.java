package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;

    @QueryMapping
    public Book findBookById(@Argument Integer bookId) { return bookRepository.findById(bookId).get();}

    @QueryMapping
    public Author findAuthorById(@Argument Integer authorId) { return authorRepository.findById(authorId).get();}

    @QueryMapping
    public Publisher findPublisherById(@Argument Integer publisherId) { return publisherRepository.findById(publisherId).get();}
}
