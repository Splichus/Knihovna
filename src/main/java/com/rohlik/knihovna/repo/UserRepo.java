package com.rohlik.knihovna.repo;

import com.rohlik.knihovna.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {


}
