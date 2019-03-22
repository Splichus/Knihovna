package com.rohlik.knihovna.controller;


import com.rohlik.knihovna.model.*;
import com.rohlik.knihovna.repo.BookRepo;
import com.rohlik.knihovna.repo.UserRepo;
import com.rohlik.knihovna.repo.VypujckaRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Arrays;
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

    //Needs refactoring to avoid the duplicity
    @PostMapping("/rest/newuser")
    public ResponseEntity<Response> registerUser(@RequestBody User user) {
        log.info("newuser accessed");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (violations.isEmpty()) {
            userRepo.save(user);
            log.info("Exited with success and saved user {}", user.toString());
            return new ResponseEntity<>(new Response("User saved"), HttpStatus.OK);
        }
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<User> error : violations) {
            errors.add(error.getMessage());
        }
        log.info("Exited with {} errors recieved from user", errors.size());
        return new ResponseEntity<>(new Response("Error(s) while saving", errors), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/rest/newbook")
    public ResponseEntity<Response> addBook(@RequestBody Book book) {
        log.info("/newbook accessed");
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        if (violations.isEmpty()) {
            bookRepo.save(book);
            log.info("Exited with succes and saved book {}", book);
            return new ResponseEntity<>(new Response("Book saved"), HttpStatus.OK);
        }
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<Book> error : violations) {
            errors.add(error.getMessage());
        }
        log.info("Exited with {} errors recieved from user", errors.size());
        return new ResponseEntity<>(new Response("Error(s) while saving", errors), HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/rest/getbook")
    public ResponseEntity<Response> getBook(@RequestBody BookRequest bookRequest) {
        log.info("/getbook accessed");
        if (userRepo.findByUsername(bookRequest.getUsername()).isPresent()) {
            if (bookRepo.findByTitle(bookRequest.getTitle()).isPresent()) {
                Vypujcka newVypujcka = new Vypujcka(userRepo.findByUsername(bookRequest.getUsername()).get(), bookRepo.findByTitle(bookRequest.getTitle()).get());
                vypujckaRepo.save(newVypujcka);
                User user = findUser(bookRequest.getUsername());
                user.setStatus(true);
                userRepo.save(user);
                return new ResponseEntity<>(new Response(bookRequest.getTitle() + " vypujcena uzivatelem"+bookRequest.getUsername()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Response("kniha" + bookRequest.getTitle() + "nenalezena"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Response("uživatel" + bookRequest.getUsername() + "nenalezen"), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/rest/returnbook")
    public ResponseEntity<Response> returnBook(@RequestBody BookRequest bookRequest) {
        log.info("/returnbook accessed");
        if (vypujckaexists(bookRequest.getUsername(), bookRequest.getTitle())) {
            Vypujcka vypujcka = findVypujcka(bookRequest.getUsername(), bookRequest.getTitle());
            vypujckaRepo.delete(vypujcka);
            User user = findUser(bookRequest.getUsername());
            if (!vypujckaRepo.findAllByUser(user).isPresent()) {
                user.setStatus(false);
                userRepo.save(user);
                return new ResponseEntity<>(new Response("odevzdano"), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new Response("error", Arrays.asList("Wrong user or book")), HttpStatus.BAD_REQUEST);
    }

    private boolean vypujckaexists(String username, String title) {
        return vypujckaRepo.findByUserAndBook(userRepo.findByUsername(username).get(), bookRepo.findByTitle(title).get()).isPresent();
    }

    private Vypujcka findVypujcka(String username, String title) {
        return vypujckaRepo.findByUserAndBook(userRepo.findByUsername(username).get(), bookRepo.findByTitle(title).get()).get();
    }
    private User findUser(String username) {
        return userRepo.findByUsername(username).get();
    }

}
