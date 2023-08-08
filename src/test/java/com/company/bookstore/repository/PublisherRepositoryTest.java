package com.company.bookstore.repository;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.model.Book; // Import your Book class here
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PublisherRepositoryTest {

    @Autowired
    private PublisherRepository publisherRepository;

    @BeforeEach
    public void setUp() {
        publisherRepository.deleteAll();
    }

    @Test
    public void getAllPublishersTest() {
        Publisher newPublisher = new Publisher("ABC Publishers", "123 Main St", "Cityville", "CA", "12345", "555-123-4567", "info@abcpublishers.com");
        publisherRepository.save(newPublisher);

        List<Publisher> newPublishers = publisherRepository.findAll();

        assertEquals(1, newPublishers.size());
    }

    @Test
    public void getPublisherById() {
        Publisher expected = new Publisher("ABC Publishers", "123 Main St", "Cityville", "CA", "12345", "555-123-4567", "info@abcpublishers.com");
        expected = publisherRepository.save(expected);

        Optional<Publisher> actual = publisherRepository.findById(expected.getPublisherId());

        assertEquals(expected, actual.get());
    }

    @Test
    public void addPublisherTest() {
        Publisher data = new Publisher("ABC Publishers", "123 Main St", "Cityville", "CA", "12345", "555-123-4567", "info@abcpublishers.com");

        Publisher expected = publisherRepository.save(data);

        Optional<Publisher> actual = publisherRepository.findById(expected.getPublisherId());

        assertEquals(expected, actual.get());
    }

    @Test
    public void updatePublisherTest() {
        Publisher data = new Publisher("ABC Publishers", "123 Main St", "Cityville", "CA", "12345", "555-123-4567", "info@abcpublishers.com");
        Publisher expected = publisherRepository.save(data);

        expected.setCity("Chicago");
        Publisher actual = publisherRepository.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void deletePublisherTest() {
        Publisher data = new Publisher("ABC Publishers", "123 Main St", "Cityville", "CA", "12345", "555-123-4567", "info@abcpublishers.com");
        Publisher expected = publisherRepository.save(data);
        Optional<Publisher> actual = publisherRepository.findById(expected.getPublisherId());

        assertEquals(expected, actual.get());

        publisherRepository.deleteById(expected.getPublisherId());

        assertThrows(NoSuchElementException.class, () -> publisherRepository.findById(expected.getPublisherId()).get());
    }

    // Test similar to above for the BookRepository and Book class
}
