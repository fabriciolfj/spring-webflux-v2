package com.github.study_webflux.controller;

import com.github.study_webflux.entities.UserEntity;
import com.github.study_webflux.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserEntity> createUser(@RequestBody final UserEntity userEntity) {
        return userService.createUser(userEntity);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserEntity>> getUserById(final Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
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
