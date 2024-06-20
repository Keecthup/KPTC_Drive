package com.example.drivedaotest.repository;

import entity.FileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<FileUser, Long> {

    FileUser findByUsername(String username);
}