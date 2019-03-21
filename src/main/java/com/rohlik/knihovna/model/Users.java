package com.rohlik.knihovna.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Users {

    List<User> users;

    public Users() {
        users = new ArrayList<>();
    }

    public Users(List<User> users) {
        this.users = users;
    }
}
