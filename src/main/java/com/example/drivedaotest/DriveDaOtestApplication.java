package com.example.drivedaotest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.example.drivedaotest.service.StorageProperties;
import com.example.drivedaotest.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class DriveDaOtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriveDaOtestApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
