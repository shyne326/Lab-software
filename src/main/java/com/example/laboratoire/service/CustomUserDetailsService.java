/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.laboratoire.service;

import com.example.laboratoire.model.CustomUserDetails;
import com.example.laboratoire.model.User;
import com.example.laboratoire.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author CHRISTIAN
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        
        Optional<User> opt = userRepository.findByFirstName(firstName);
        
        opt.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return opt.map(CustomUserDetails::new).get();

    }
    
}
