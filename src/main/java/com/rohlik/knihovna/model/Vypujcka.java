package com.rohlik.knihovna.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Vypujcka {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    User user;
    @ManyToOne
    Book book;

    public Vypujcka() {
    }

    public Vypujcka(User user, Book book) {
        this.user = user;
        this.book = book;
    }
}
