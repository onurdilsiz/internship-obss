package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserById(UUID id);
    List<User> findUsersByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name,String surname, Pageable pageable);
    @Query("from User")
    List<User> findAllUsers(Pageable pageable);

    Optional<User> findUserByUsername(String username);



}
