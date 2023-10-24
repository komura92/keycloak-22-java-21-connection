package com.example.keycloak.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ExampleController.PATH)
public class ExampleController {
    public static final String PATH = "example";

    @GetMapping(value = "secured")
    public String securedGet() {
        return "Secured content";
    }

    @GetMapping(value = "unsecured")
    public String unsecuredGet() {
        return "Unsecured content";
    }
}
