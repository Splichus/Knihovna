package com.rohlik.knihovna.repo;

import com.rohlik.knihovna.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookRepo extends CrudRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
}
