package com.rohlik.knihovna.model;

import lombok.Data;

import java.util.List;

@Data
public class Response {

    String message;
    List<String> errors;

    public Response(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public Response(String message) {
        this.message = message;
    }
}
