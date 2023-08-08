package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @BeforeEach
    public void setUp() throws Exception {
        authorRepository.deleteAll();
    }

    @Test
    public void getAllAuthorsTest() throws Exception{
        Author newAuthor = new Author(1,"King", "Stephen", "Hollywood", "Los Angeles", "CA", "11100", "111-222-333", "sking@gmail.com");
        authorRepository.save(newAuthor);

        List<Author> newAuthors = authorRepository.findAll();

        assertEquals(1, newAuthors.size());
    }

    @Test
    public void getAuthorById() throws Exception{
        Author expected = new Author(1,"King", "Stephen", "Hollywood", "Los Angeles", "CA", "11100", "111-222-333", "sking@gmail.com");
        expected = authorRepository.save(expected);

        Optional<Author> actual = authorRepository.findById(expected.getAuthorId());

        assertEquals(expected, actual.get());
    }

    @Test
    public void addAuthorTest() throws Exception{
        Author data = new Author("King", "Stephen", "Hollywood", "Los Angeles", "CA", "11100", "111-222-333", "sking@gmail.com");

        Author expected = authorRepository.save(data);

        Optional<Author> actual = authorRepository.findById(expected.getAuthorId());

        assertEquals(expected, actual.get());
    }

    @Test
    public void updateAuthorTest() throws Exception{
        Author data = new Author("King", "Stephen", "Hollywood", "Los Angeles", "CA", "11100", "111-222-333", "sking@gmail.com");
        Author expected = authorRepository.save(data);

        expected.setCity("Chicago");
        Author actual = authorRepository.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void deleteAuthorTest() throws Exception {
        Author data = new Author("King", "Stephen", "Hollywood", "Los Angeles", "CA", "11100", "111-222-333", "sking@gmail.com");
        Author expected = authorRepository.save(data);
        Optional<Author> actual = authorRepository.findById(expected.getAuthorId());

        assertEquals(expected, actual.get());

        authorRepository.deleteById(expected.getAuthorId());

        assertThrows(NoSuchElementException.class, () -> authorRepository.findById(expected.getAuthorId()).get());

    }
}
