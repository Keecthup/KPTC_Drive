package com.example.drivedaotest.DB;

import com.example.drivedaotest.entity.FileUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUserRepository extends JpaRepository<FileUser, Long> {
    public FileUser findByLogin(String login);
}