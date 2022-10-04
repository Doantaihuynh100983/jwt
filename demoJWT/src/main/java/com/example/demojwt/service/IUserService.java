package com.example.demojwt.service;

import com.example.demojwt.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String name); // tìm kiếm user có tồn tại hay ko
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User save(User user);
}
