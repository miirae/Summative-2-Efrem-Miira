package com.company.bookstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@WebMvcTest(PublisherController.class)
public class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublisherRepository publisherRepo;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void addPublisherTest() throws Exception {
        Publisher newPublisher = new Publisher(1, "ABC Publishers", "123 Main St", "Cityville", "CA", "12345", "555-123-4567", "info@abcpublishers.com");

        String newPublisherJson = mapper.writeValueAsString(newPublisher);

        mockMvc.perform(post("/publishers")
                        .content(newPublisherJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void updatePublisherTest() throws Exception {
        Publisher newPublisher = new Publisher(1, "ABC Publishers", "123 Main St", "Cityville", "CA", "12345", "555-123-4567", "info@abcpublishers.com");

        String newPublisherJson = mapper.writeValueAsString(newPublisher);

        mockMvc.perform(put("/publishers")
                        .content(newPublisherJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deletePublisherTest() throws Exception {
        mockMvc.perform(delete("/publishers/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void getPublisherByIdTest() throws Exception {
        mockMvc.perform(get("/publishers/{id}", "123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
