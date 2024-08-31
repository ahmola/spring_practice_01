package com.example.prac01.repository;

import com.example.prac01.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    Boolean existsByEmail(String email);
}
