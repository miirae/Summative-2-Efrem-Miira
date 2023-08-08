
package com.company.bookstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookRepository bookRepo;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getAllBooksTests() throws Exception {
        mockMvc.perform(get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addBookTest() throws Exception {
        Author bookAuthor = new Author(1,"King", "Stephen", "Hollywood", "Los Angeles", "CA", "11100", "111-222-333", "sking@gmail.com");
        Publisher bookPublisher = new Publisher(1, "McGraw-Hill", "Hollywood", "Los Angeles","CA", "11100", "111-222-333", "mcg@gmail.com");
        String bookDate = new Date().toString();
        Book newBook = new Book(1, "9783161484100", bookDate, bookAuthor, "An interesting book", bookPublisher, new BigDecimal(29.99));

        String newBookJson = mapper.writeValueAsString(newBook);

        mockMvc.perform(post("/books")
                        .content(newBookJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void updateBookTest() throws Exception {
        Book newBook = new Book();
        newBook.setBookId(1);
        // Set other book properties

        String newBookJson = mapper.writeValueAsString(newBook);

        mockMvc.perform(put("/books")
                        .content(newBookJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteBookTest() throws Exception {
        mockMvc.perform(delete("/books/{bookId}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void getBookByIdTest() throws Exception {
        mockMvc.perform(get("/books/{bookId}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getBooksByAuthor() throws Exception {
        Author bookAuthor = new Author(1,"King", "Stephen", "Hollywood", "Los Angeles", "CA", "11100", "111-222-333", "sking@gmail.com");

        String bookAuthorJson = mapper.writeValueAsString(bookAuthor);

        mockMvc.perform(post("/books/author")
                        .content(bookAuthorJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
