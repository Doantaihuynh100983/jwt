package com.example.demojwt.controller;

import com.example.demojwt.dto.request.SignIn;
import com.example.demojwt.dto.request.SignUpForm;
import com.example.demojwt.dto.response.JwtReponse;
import com.example.demojwt.dto.response.ResponseMesseage;
import com.example.demojwt.model.Role;
import com.example.demojwt.model.RoleName;
import com.example.demojwt.model.User;
import com.example.demojwt.security.jwt.JwtProvider;
import com.example.demojwt.security.userpincal.UserPrincial;
import com.example.demojwt.service.impl.RoleServiceImpl;
import com.example.demojwt.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class AuthorController {

    private static  final Logger logger = LoggerFactory.getLogger(AuthorController.class);


    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/singup")
    public ResponseEntity<?> register(@RequestBody SignUpForm signUpForm){
        if (userService.existsByUsername(signUpForm.getUserName())){
            return new ResponseEntity<>(new ResponseMesseage("The username is existed") , HttpStatus.OK);
        }
        if (userService.existsByEmail(signUpForm.getEmail())){
            return new ResponseEntity<>(new ResponseMesseage("The email is existed") , HttpStatus.OK);
        }

        User user = new User(signUpForm.getName() , signUpForm.getUserName() , signUpForm.getEmail() , passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRole = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRole.forEach(role->{
            switch (role){
                case "ADMIN" :
                    Role adminRole = roleService.findByRoleName(RoleName.ADMIN).orElseThrow(()-> new RuntimeException("Role not found"));
                    roles.add(adminRole);
                    break;
                case "USER" :
                    Role userRole = roleService.findByRoleName(RoleName.USER).orElseThrow(()-> new RuntimeException("Role not found"));
                    roles.add(userRole);
                    break;
            }
        });
        user.setRoles(roles);
        userService.save(user);

        return new ResponseEntity<>(new ResponseMesseage("Create success"),HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignIn signIn){
        String token = "";
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signIn.getUserName() , signIn.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
             token = jwtProvider.createToken(authentication);
            UserPrincial userPrincial = (UserPrincial) authentication.getPrincipal();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(new JwtReponse(token),HttpStatus.OK);

    }
}
