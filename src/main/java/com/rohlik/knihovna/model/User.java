package com.rohlik.knihovna.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    Long id;
    String username;
    String email;
    String password;
    boolean status;
    @OneToMany
    List<Vypujcka> vypujcky;

}
