package com.rohlik.knihovna.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MainRestController.class, secure = false)
@SpringBootTest
public class MainRestControllerTest {

    @Autowired
    MainRestController mainRestController;
    @Autowired
    MockMvc mockMvc;



}