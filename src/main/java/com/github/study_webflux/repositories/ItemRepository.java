package com.github.study_webflux.repositories;

import com.github.study_webflux.entities.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ItemRepository extends ReactiveCrudRepository<Item, Long> {

    Flux<Item> findByName(String name);
}
