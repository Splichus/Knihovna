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
    private Date borrowedWhen;
    private Date returnedWhen;
    @ManyToOne
    User user;
    @ManyToOne
    Book book;




}
