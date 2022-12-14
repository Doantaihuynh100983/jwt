package com.example.demojwt.service.impl;

import com.example.demojwt.model.User;
import com.example.demojwt.repository.IUserRepository;
import com.example.demojwt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public Optional<User> findByUsername(String name) {
        return iUserRepository.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return iUserRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return iUserRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return iUserRepository.save(user);
    }
}
