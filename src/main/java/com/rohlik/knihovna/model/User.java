package com.rohlik.knihovna.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    Long id;
    @Size(min = 5, message = "Uživatelské jméno musí mít alespoň 5 znaků")
    @NotNull(message = "Chybí username")
    String username;
    @Size(min = 1, message = "email musí mít alespoň jeden znak")
    @NotNull(message = "Chybí email")
    String email;
    @Size(min = 1, message = "heslo musí mít alespoň jeden znak")
    @NotNull(message = "Chybí heslo")
    String password;
    boolean status;

}
