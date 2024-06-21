package com.example.drivedaotest.services;

import com.example.drivedaotest.DB.FileUserRepository;
import com.example.drivedaotest.entity.FileUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private FileUserRepository fileUserRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException{
        FileUser fileUser = fileUserRepository.findByLogin(login);

        if(fileUser != null){
            return User.withUsername(fileUser.getLogin()).password(fileUser.getPassword()).build();
        } else{
            throw new UsernameNotFoundException(login);
        }
    }
}
