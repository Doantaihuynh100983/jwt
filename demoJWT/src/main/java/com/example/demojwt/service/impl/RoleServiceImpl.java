package com.example.demojwt.service.impl;


import com.example.demojwt.model.Role;
import com.example.demojwt.model.RoleName;
import com.example.demojwt.repository.IRoleRepository;
import com.example.demojwt.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository iRoleRepository;


    @Override
    public Optional<Role> findByRoleName(RoleName roleName) {
        return iRoleRepository.findByRoleName(roleName);
    }
}
