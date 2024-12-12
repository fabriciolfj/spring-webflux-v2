package com.github.study_webflux.controller;

import com.github.study_webflux.entities.UserEntity;
import com.github.study_webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Mono<UserEntity> createUser(@RequestBody final UserEntity userEntity) {
        return userService.createUser(userEntity);
    }

    @GetMapping("/{id}")
    public Mono<UserEntity> getUserById(final Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/name/{username}")
    public Mono<UserEntity> getUserByUsername(final String username) {
        return userService.getUser(username);
    }

    @GetMapping
    public Flux<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
}
