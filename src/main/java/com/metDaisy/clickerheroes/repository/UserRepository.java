package com.metDaisy.clickerheroes.repository;

import com.metDaisy.clickerheroes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsUserByNameAndPassword(String name, String password);
}
