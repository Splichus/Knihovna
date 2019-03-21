package com.rohlik.knihovna.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "Author is missing")
    @Size(min = 1, message = "Author is missing")
    private String author;
    @NotNull(message = "Title is missing")
    @Size(min = 1, message = "Title is missing")
    private String title;

    public Book() {
    }

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }
}

