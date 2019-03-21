package com.rohlik.knihovna.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Book {

    private static Integer onShelf;

    @Id
    @GeneratedValue
    private Long id;
    private String author;
    private String title;
    private String iban;
}

