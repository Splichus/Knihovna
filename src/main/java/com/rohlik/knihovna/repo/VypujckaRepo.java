package com.rohlik.knihovna.repo;

import com.rohlik.knihovna.model.Book;
import com.rohlik.knihovna.model.User;
import com.rohlik.knihovna.model.Vypujcka;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VypujckaRepo extends CrudRepository<Vypujcka, Long> {

    Optional<Vypujcka> findByUserAndBook(User user, Book book);
    Optional<List<Vypujcka>> findAllByUser (User user);
}
