package com.example.demojwt.security.userpincal;

import com.example.demojwt.model.User;
import com.example.demojwt.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository iUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserRepository.findByUsername(username)
                                    .orElseThrow(() -> new UsernameNotFoundException("User not found -> user name or password" + username));
        return UserPrincial.build(user);
    }
}
