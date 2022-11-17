package com.bookworld.api.auth;

import com.bookworld.api.admin.Admin;
import com.bookworld.api.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminService.getByEmail(username);
        if (admin.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        Admin adminFound = admin.get();
        return new User(adminFound.getEmail(), adminFound.getPassword(), new ArrayList<>());
    }
}
