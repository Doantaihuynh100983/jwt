package com.example.demojwt.repository;

import com.example.demojwt.model.Role;
import com.example.demojwt.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository  extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleName(RoleName roleName);

}
