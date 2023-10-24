package com.example.keycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KeycloakSecuredApp {
    public static void main(String[] args) {
        SpringApplication.run(KeycloakSecuredApp.class, args);
    }
}