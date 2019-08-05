package com.drink.api.services;

import com.drink.api.assemblers.DrinkResourceAssembler;
import com.drink.api.controllers.DrinkController;
import com.drink.api.domain.Drink;
import com.drink.api.exceptions.DrinkNotFoundException;
import com.drink.api.repo.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class DrinkServicesImpl implements DrinkServices {

    @Autowired
    private DrinkRepository repository;

    @Autowired
    private DrinkResourceAssembler assembler;

    @Override
    public ResponseEntity getAllDrinks() {
        List<Resource<Drink>> drinks = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        Resources<Resource<Drink>> resources = new Resources<>(drinks,
                linkTo(methodOn(DrinkController.class).getAllDrinks()).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @Override
    public ResponseEntity createDrink(Drink newDrink) {

        Drink savedDrink = repository.save(newDrink);

        return getCreatedResponse(savedDrink);
    }

    @Override
    public ResponseEntity getOneDrink(String id) {

        Drink drink = repository.findById(id)
                .orElseThrow(() -> new DrinkNotFoundException(id));

        return getOkResponse(drink);
    }

    @Override
    public ResponseEntity updateDrink(String id, Drink newDrink) {

        return repository.findById(id)
                .map(drink -> {
                    final boolean created = true;
                    drink.setTitle(newDrink.getTitle());
                    drink.setIngredients(newDrink.getIngredients());
                    drink.setMeasurements(newDrink.getMeasurements());
                    drink.setInstructions(newDrink.getInstructions());
                    return getOkResponse(repository.save(drink));
                })
                .orElseGet(() -> {
                    newDrink.setId(id);
                    return getCreatedResponse(repository.save(newDrink));
                });
    }

    @Override
    public ResponseEntity deleteDrink(String id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    private ResponseEntity getCreatedResponse(Drink drink) {
        return ResponseEntity
                .created(assembler.getDrinkLink(drink))
                .body(assembler.toResource(drink));
    }

    private ResponseEntity getOkResponse(Drink drink) {
        return ResponseEntity.ok(assembler.toResource(drink));
    }
}
