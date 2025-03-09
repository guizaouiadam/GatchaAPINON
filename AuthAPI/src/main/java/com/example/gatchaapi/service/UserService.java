package com.example.gatchaapi.service;


import com.example.gatchaapi.model.User;
import com.example.gatchaapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserProfile(String token) {
        return userRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));
    }
}
