package com.github.study_webflux.service;

import com.github.study_webflux.entities.UserEntity;
import com.github.study_webflux.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<UserEntity> createUser(final UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public Mono<UserEntity> getUser(final String username) {
        return userRepository.findByName(username);
    }

    public Mono<UserEntity> getUserById(final Long id) {
        return userRepository.findById(id);
    }

    public Flux<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
