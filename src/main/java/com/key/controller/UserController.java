package com.key.controller;

import com.key.entity.User;
import com.key.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private KeycloakService keycloakService;

    @GetMapping("/home")
    public String home() {
        return "Welcome to Keycloak API";
    }

    @PostMapping("/save-user")
    public String createUser(@RequestBody User user) {
        keycloakService.createUser(user);
        return "User created successfully by Keycloak";
    }
}
