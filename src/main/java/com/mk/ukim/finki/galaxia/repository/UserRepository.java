package com.mk.ukim.finki.galaxia.repository;


import com.mk.ukim.finki.galaxia.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
