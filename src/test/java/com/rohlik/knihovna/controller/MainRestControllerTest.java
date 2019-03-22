package com.rohlik.knihovna.controller;

import com.rohlik.knihovna.repo.BookRepo;
import com.rohlik.knihovna.repo.UserRepo;
import com.rohlik.knihovna.repo.VypujckaRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.nio.charset.Charset;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MainRestController.class)
public class MainRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    BookRepo bookRepo;
    @MockBean
    UserRepo userRepo;
    @MockBean
    VypujckaRepo vypujckaRepo;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Test
    public void savesValidUser() throws Exception {
        mockMvc.perform(post("/rest/newuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"aaaaaa\", \"email\" : \"a\", \"password\": \"a\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.message", is("User saved")));
    }

    @Test
    public void returnserror() throws Exception {
        mockMvc.perform(post("/rest/newuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"aa\", \"email\" : \"a\", \"password\": \"a\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.message", is("Error(s) while saving")));
    }
}