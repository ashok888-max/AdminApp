package com.app.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.demo.entity.Admin;
import com.app.demo.repository.AdminRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByAdminId(identifier);
        if (admin == null) {
            admin = adminRepository.findByEmail(identifier);
        }

        if (admin == null) {
            throw new UsernameNotFoundException("User not found with identifier: " + identifier);
        }

        return new org.springframework.security.core.userdetails.User(
                admin.getAdminId(),
                admin.getPassword(),
                new ArrayList<>() // You can add roles or authorities here
        );
    }
}
