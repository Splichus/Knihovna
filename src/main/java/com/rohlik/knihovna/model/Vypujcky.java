package com.rohlik.knihovna.model;

import lombok.Data;

import java.util.List;

@Data
public class Vypujcky {

    List<Vypujcka> vypujcky;

    public Vypujcky() {
    }

    public Vypujcky(List<Vypujcka> vypujcky) {
        this.vypujcky = vypujcky;
    }
}
