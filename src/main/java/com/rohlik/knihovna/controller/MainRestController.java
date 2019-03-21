package com.rohlik.knihovna.controller;


import com.rohlik.knihovna.model.*;
import com.rohlik.knihovna.repo.BookRepo;
import com.rohlik.knihovna.repo.UserRepo;
import com.rohlik.knihovna.repo.VypujckaRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
public class MainRestController {

    BookRepo bookRepo;
    UserRepo userRepo;
    VypujckaRepo vypujckaRepo;
    ValidatorFactory factory;
    Validator validator;

    @Autowired
    public MainRestController(BookRepo bookRepo, UserRepo userRepo, VypujckaRepo vypujckaRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
        this.vypujckaRepo = vypujckaRepo;
        this.factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @PostMapping("/rest/newuser")
    public Confirmation registerUser(@RequestBody User user) {
        log.info("newuser accessed");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (violations.isEmpty()) {
            userRepo.save(user);
            log.info("Exited with success and saved user {}", user.toString());
            return new Confirmation(201, "User saved");
        }
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<User> error : violations) {
            errors.add(error.getMessage());
        }
        log.info("Exited with {} errors recieved from user", errors.size());
        return new Confirmation(400, "error", errors);
    }

    @PostMapping("/rest/newbook")
    public Confirmation addBook(@RequestBody Book book) {
        log.info("/newbook accessed");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        if (violations.isEmpty()) {
            bookRepo.save(book);
            log.info("Exited with succes and saved book {}", book);
            return new Confirmation(201, "Book Saved");
        }
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<Book> error : violations) {
            errors.add(error.getMessage());
        }
        log.info("Exited with {} errors recieved from user", errors.size());
        return new Confirmation(400, "error", errors);

    }

    @PostMapping("/rest/getbook")
    public Confirmation getBook(@RequestHeader String username, @RequestBody BookRequest title) {
        if (userRepo.findByUsername(username).isPresent()) {
            if (bookRepo.findByTitle(title.getBookname()).isPresent()) {
                vypujckaRepo.save(new Vypujcka(userRepo.findByUsername(username).get(), bookRepo.findByTitle(title.getBookname()).get()));
                return new Confirmation(201, title + "vypujcena");
            }
            return new Confirmation(400, "kniha" +title+ "nenalezena");
        }
        return new Confirmation(400, "uživatel" +username+ "nenalezen");
    }


    @GetMapping("/rest/getusers")
    public Users getUsers(){
        log.info("getusers accessed");
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        log.info("Exited /getusers with list of {} users", users.size());
        return new Users(users);
    }
    @GetMapping("/resr/getvypujcky")
    public Vypujcky getVypujcky(){
        List<Vypujcka> users = new ArrayList<>();
        vypujckaRepo.findAll().forEach(users::add);
        log.info("Exited /getusers with list of {} users", users.size());
        return new Vypujcky(users);
    }

}
