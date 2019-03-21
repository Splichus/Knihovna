package com.rohlik.knihovna.repo;

import com.rohlik.knihovna.model.Vypujcka;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VypojckaRepo extends CrudRepository<Vypujcka, Long> {



}
