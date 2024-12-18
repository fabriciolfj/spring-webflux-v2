package com.github.study_webflux.service;

import com.github.study_webflux.entities.Item;
import com.github.study_webflux.repositories.ItemRepository;
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

    @Transactional
    public Mono<Item> saveItem(final Item item) {
        return itemRepository.save(item);
    }

    public Flux<Item> findItemsByName(final String name) {
        return itemRepository.findByName(name)
                .onBackpressureBuffer(100, BufferOverflowStrategy.ERROR)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(1)));
    }
}
