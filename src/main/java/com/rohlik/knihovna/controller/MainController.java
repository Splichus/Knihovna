package com.rohlik.knihovna.controller;

import com.rohlik.knihovna.repo.BookRepo;
import com.rohlik.knihovna.repo.UserRepo;
import com.rohlik.knihovna.repo.VypojckaRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Slf4j
@Controller
public class MainController {

    BookRepo bookRepo;
    UserRepo userRepo;
    VypojckaRepo vypojckaRepo;
    ValidatorFactory factory;
    Validator validator;

    @Autowired
    public MainController(BookRepo bookRepo, UserRepo userRepo, VypojckaRepo vypojckaRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
        this.vypojckaRepo = vypojckaRepo;
        this.factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

}
