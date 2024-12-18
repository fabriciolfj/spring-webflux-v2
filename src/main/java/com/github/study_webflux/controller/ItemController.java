package com.github.study_webflux.controller;

import com.github.study_webflux.entities.Item;
import com.github.study_webflux.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public Mono<Item> createItem(@RequestBody final Item item) {
        return itemService.saveItem(item);
    }

    @GetMapping("/name/{name}")
    public Flux<Item> findItemsByName(@PathVariable("name") final String name) {
        return itemService.findItemsByName(name);
    }
}
