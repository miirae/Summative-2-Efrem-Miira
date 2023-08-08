package com.company.bookstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.company.bookstore.model.Author;
import com.company.bookstore.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AuthorRepository authorRepo;


    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void addAuthorTest() throws Exception {
        Author newAuthor = new Author(1,"King", "Stephen", "Hollywood", "Los Angeles", "CA", "11100", "111-222-333", "sking@gmail.com");


        String newAuthorJson = mapper.writeValueAsString(newAuthor);

        mockMvc.perform(post("/authors")
                        .content(newAuthorJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void updateAuthorTest() throws Exception {
        Author newAuthor = new Author(1,"King", "Stephen", "Hollywood", "Los Angeles", "CA", "11100", "111-222-333", "sking@gmail.com");

        String newAuthorJson = mapper.writeValueAsString(newAuthor);

        mockMvc.perform(put("/authors")
                        .content(newAuthorJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteAuthorTest() throws Exception {
        mockMvc.perform(delete("/authors/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void getAuthorByIdTest() throws Exception {
        mockMvc.perform(get("/authors/{id}", "123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
