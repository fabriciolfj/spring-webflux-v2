package com.github.study_webflux;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.study_webflux.controller.UserController;
import com.github.study_webflux.entities.UserEntity;
import com.github.study_webflux.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    private WebTestClient webTestClient;
    private UserService userService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
        ObjectMapper objectMapper = new ObjectMapper();
        webTestClient = WebTestClient
                .bindToController(userController)
                .configureClient()
                .codecs(configurer -> {
                    configurer.defaultCodecs().jackson2JsonEncoder(
                            new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON)
                    );
                    configurer.defaultCodecs().jackson2JsonDecoder(
                            new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON)
                    );
                })
                .build();
    }

    @Test
    public void getUserById_ShouldReturnUser() {
        UserEntity user = new UserEntity(1L, "John Doe", 21);
        when(userService.getUserById(any())).thenReturn(Mono.just(user));

        webTestClient.get()
                .uri("/api/users/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("John Doe");
    }

    @Test
    public void getUserById_WhenNotFound_ShouldReturn404() {
        when(userService.getUserById(any())).thenReturn(Mono.empty());

        webTestClient.get()
                .uri("/api/users/{id}", 99)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void createUser_ShouldReturnCreatedUser() {
        // Arrange
        UserEntity newUser = new UserEntity(null, "Jane Doe", 21);
        UserEntity createdUser = new UserEntity(2L, "Jane Doe", 21);

        when(userService.createUser(any(UserEntity.class))).thenReturn(Mono.just(createdUser));

        // Act & Assert
        webTestClient.post()
                .uri("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newUser)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(2)
                .jsonPath("$.name").isEqualTo("Jane Doe");
    }
}
