package com.company.bookstore.repository;

import com.company.bookstore.model.Book;
import com.company.bookstore.model.Author;
import com.company.bookstore.model.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();
    }

    @Test
    public void getAllBooksTest() {
        Author author = new Author("Doe", "John", "123 Main St", "Springfield", "IL", "12345", "555-123-4567", "john.doe@example.com");
        author = authorRepository.save(author);

        Publisher publisher = new Publisher("ABC Publishers", "123 Main St", "Cityville", "CA", "12345", "555-123-4567", "info@abcpublishers.com");
        publisher = publisherRepository.save(publisher);

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(now);

        Book newBook = new Book("978-1234567890", formattedDate, author, "Sample Book", publisher, BigDecimal.valueOf(29.99));
        bookRepository.save(newBook);

        List<Book> newBooks = bookRepository.findAll();

        assertEquals(1, newBooks.size());
    }

    @Test
    public void getBookById() {
        Author author = new Author("Doe", "John", "123 Main St", "Springfield", "IL", "12345", "555-123-4567", "john.doe@example.com");
        author = authorRepository.save(author);

        Publisher publisher = new Publisher("ABC Publishers", "123 Main St", "Cityville", "CA", "12345", "555-123-4567", "info@abcpublishers.com");
        publisher = publisherRepository.save(publisher);

        Date now = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(now);

        Book expected = new Book("978-1234567890", formattedDate , author, "Sample Book", publisher, BigDecimal.valueOf(29.99));
        expected = bookRepository.save(expected);

        Optional<Book> actual = bookRepository.findById(expected.getBookId());

        assertEquals(expected, actual.get());
    }

    @Test
    public void addBookTest() {
        Author author = new Author("Doe", "John", "123 Main St", "Springfield", "IL", "12345", "555-123-4567", "john.doe@example.com");
        author = authorRepository.save(author);

        Publisher publisher = new Publisher("ABC Publishers", "123 Main St", "Cityville", "CA", "12345", "555-123-4567", "info@abcpublishers.com");
        publisher = publisherRepository.save(publisher);

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(now);

        Book data = new Book("978-1234567890", formattedDate , author, "Sample Book", publisher, BigDecimal.valueOf(29.99));

        Book expected = bookRepository.save(data);

        Optional<Book> actual = bookRepository.findById(expected.getBookId());

        assertEquals(expected, actual.get());
    }

    @Test
    public void updateBookTest() {
        Author author = new Author("Doe", "John", "123 Main St", "Springfield", "IL", "12345", "555-123-4567", "john.doe@example.com");
        author = authorRepository.save(author);

        Publisher publisher = new Publisher("ABC Publishers", "123 Main St", "Cityville", "CA", "12345", "555-123-4567", "info@abcpublishers.com");
        publisher = publisherRepository.save(publisher);

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(now);

        Book data = new Book("978-1234567890", formattedDate , author, "Sample Book", publisher, BigDecimal.valueOf(29.99));
        Book expected = bookRepository.save(data);

        expected.setPrice(BigDecimal.valueOf(39.99));
        Book actual = bookRepository.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void deleteBookTest() {
        Author author = new Author("Doe", "John", "123 Main St", "Springfield", "IL", "12345", "555-123-4567", "john.doe@example.com");
        author = authorRepository.save(author);

        Publisher publisher = new Publisher("ABC Publishers", "123 Main St", "Cityville", "CA", "12345", "555-123-4567", "info@abcpublishers.com");
        publisher = publisherRepository.save(publisher);

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(now);

        Book data = new Book("978-1234567890",formattedDate , author, "Sample Book", publisher, BigDecimal.valueOf(29.99));
        Book expected = bookRepository.save(data);
        Optional<Book> actual = bookRepository.findById(expected.getBookId());

        assertEquals(expected, actual.get());

        bookRepository.deleteById(expected.getBookId());

        assertThrows(NoSuchElementException.class, () -> bookRepository.findById(expected.getBookId()).get());
    }
}
