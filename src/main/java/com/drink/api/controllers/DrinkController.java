package com.drink.api.controllers;

import com.drink.api.assemblers.DrinkResourceAssembler;
import com.drink.api.daos.DrinkRepository;
import com.drink.api.exceptions.DrinkNotFoundException;
import com.drink.api.models.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/drinks")
public class DrinkController {

    @Autowired
    private DrinkRepository repository;
    @Autowired
    private DrinkResourceAssembler assembler;

    // Aggregate root

    @GetMapping()
    public ResponseEntity<?> all() {

        List<Resource<Drink>> drinks = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        Resources<Resource<Drink>> resources = new Resources<>(drinks,
                linkTo(methodOn(DrinkController.class).all()).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @PostMapping()
    public ResponseEntity<?> newDrink(@RequestBody Drink newDrink) {

        Drink savedDrink = repository.save(newDrink);

        return ResponseEntity
                .created(linkTo(methodOn(DrinkController.class).one(newDrink.getId())).toUri())
                .body(assembler.toResource(savedDrink));
    }

    // Single item

    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable String id) {

        Drink drink = repository.findById(id)
                .orElseThrow(() -> new DrinkNotFoundException(id));

        return ResponseEntity.ok(assembler.toResource(drink));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceDrink(@RequestBody Drink newDrink, @PathVariable String id) {

        Drink updatedDrink = repository.findById(id)
                .map(drink -> {
                    drink.setName(newDrink.getName());
                    return repository.save(drink);
                })
                .orElseGet(() -> {
                    newDrink.setId(id);
                    return repository.save(newDrink);
                });

        return ResponseEntity
                .created(linkTo(methodOn(DrinkController.class).one(updatedDrink.getId())).toUri())
                .body(assembler.toResource(updatedDrink));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDrink(@PathVariable String id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
