package com.rohlik.knihovna.repo;

import com.rohlik.knihovna.model.Vypujcka;
import org.springframework.data.repository.CrudRepository;

public interface History extends CrudRepository<Vypujcka, Long> {

}
