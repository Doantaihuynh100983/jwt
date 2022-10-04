package com.example.demojwt.service;

import com.example.demojwt.model.Role;
import com.example.demojwt.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByRoleName(RoleName roleName);

}
