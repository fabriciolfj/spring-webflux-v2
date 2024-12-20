package com.github.study_webflux.service;

import com.github.study_webflux.entities.Item;
import com.github.study_webflux.repositories.ItemRepository;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ConnectionFactory connectionFactory;

    @Transactional
    public Mono<Item> saveItem(final Item item) {
        return itemRepository.save(item);
    }

    public Flux<Item> findItemsByName(final String name) {
        return itemRepository.findByName(name)
                .onBackpressureBuffer(100, BufferOverflowStrategy.ERROR)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(1)));
    }

    public Mono<Boolean> updateRecord(final long id, final int currentVersion, final int newVersion) {
        return Mono.from(connectionFactory.create())
                .flatMap(connection -> Mono.from(connection.createStatement(
                                        "UPDATE records SET version = $1 WHERE id = $2 AND version = $3")
                                .bind(0, newVersion)  // Changed from "$1" to 0
                                .bind(1, id)          // Changed from "$2" to 1
                                .bind(2, currentVersion)  // Changed from "$3" to 2
                                .execute())
                        .flatMap(result -> Mono.from(result.getRowsUpdated()))
                        .map(rowsUpdated -> rowsUpdated == 1)
                        .doFinally(signalType -> connection.close())  // Close connection, not factory
                );
    }
}
