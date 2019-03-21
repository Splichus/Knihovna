package com.rohlik.knihovna.repo;

import com.rohlik.knihovna.model.Vypujcka;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VypujckaRepo extends CrudRepository<Vypujcka, Long> {

}
