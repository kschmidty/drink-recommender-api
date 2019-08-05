package com.drink.api.assemblers;

import com.drink.api.domain.Drink;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.drink.api.controllers.DrinkController;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class DrinkResourceAssembler implements ResourceAssembler<Drink, Resource<Drink>> {

    @Override
    public Resource<Drink> toResource(Drink drink) {

        return new Resource<>(drink,
                linkTo(methodOn(DrinkController.class).getOneDrink(drink.getId())).withSelfRel(),
                linkTo(methodOn(DrinkController.class).getAllDrinks()).withRel("drinks"));
    }

    public URI getDrinkLink(Drink drink) {

        return linkTo(methodOn(DrinkController.class).getOneDrink(drink.getId())).toUri();
    }
}
