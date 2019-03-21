package com.rohlik.knihovna.model;

import lombok.Data;

import java.util.List;

@Data
public class Confirmation {

    int status;
    String message;
    List<String> errors;

    public Confirmation(int status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public Confirmation(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
