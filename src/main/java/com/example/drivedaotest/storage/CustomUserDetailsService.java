package com.example.drivedaotest.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.drivedaotest.repository.UserRepository;
import com.example.drivedaotest.entity.FileUser;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FileUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserLogin())
                .password(user.getUserPassword())
                .build();
    }

    public void registerNewUser(FileUser user) {
        if (userRepository.findByUsername(user.getUserLogin()) != null) {
            throw new IllegalArgumentException("User with this username already exists");
        }

        FileUser newUser = new FileUser();
        newUser.setUserLogin(user.getUserLogin());
        newUser.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

        userRepository.save(newUser);
    }
}
