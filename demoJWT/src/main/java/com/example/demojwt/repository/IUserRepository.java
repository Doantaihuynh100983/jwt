package com.example.demojwt.repository;

import com.example.demojwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User , Long> {
    Optional<User> findByUsername(String name); // tìm kiếm user có tồn tại hay ko
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
