package com.rohlik.knihovna.controller;

import com.rohlik.knihovna.model.User;
import com.rohlik.knihovna.repo.BookRepo;
import com.rohlik.knihovna.repo.UserRepo;
import com.rohlik.knihovna.repo.VypujckaRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class MainController {

    BookRepo bookRepo;
    UserRepo userRepo;
    VypujckaRepo vypojckaRepo;
    ValidatorFactory factory;
    Validator validator;

    @Autowired
    public MainController(BookRepo bookRepo, UserRepo userRepo, VypujckaRepo vypojckaRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
        this.vypojckaRepo = vypojckaRepo;
        this.factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @GetMapping("/")
    public String home() {
        log.info("otevrena homepage");
        return "home";
    }

    @GetMapping("/newuser")
    public String newUser (Model model) {
        return "newuser";
    }

    @GetMapping("/login")
    public String login (Model model) {
        return "login";
    }

    @PostMapping("/add")
    public String addUser(User user, Model model) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (violations.isEmpty()) {
            userRepo.save(user);
            return "redirect:/login";
        } else {
            List<String> errors = new ArrayList<>();
            for (ConstraintViolation<User> error : violations) {
                errors.add(error.getMessage());
                model.addAttribute("errors", errors);
            }
            return "newuser";
        }
    }
    @PostMapping("/login")
    public String loginProcess(String username, String password, Model model, RedirectAttributes attributes) {
        String error;
        if (!userRepo.findByUsername(username).isPresent()) {
            error = "uživatel nenalezen";
            model.addAttribute("error", error);
            return "login";
        } else if (!userRepo.findByUsername(username).get().getPassword().equals(password)) {
            error = "špatné heslo";
            model.addAttribute("error", error);
            return "login";
        } else {
            attributes.addAttribute("user", username);
            return "redirect:/main";
        }
    }
    @GetMapping("/main")
    public String main(String username) {
        User activeUser = userRepo.findByUsername(username).get();
        return "hadoop";

    }

}
