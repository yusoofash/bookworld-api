package com.bookworld.api.admin;

import com.bookworld.api.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Optional<Admin> getByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
