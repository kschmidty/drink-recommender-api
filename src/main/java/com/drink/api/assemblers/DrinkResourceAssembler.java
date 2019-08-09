package com.drink.api.assemblers;

import com.drink.api.controllers.DrinkController;
import com.drink.api.domain.Drink;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class DrinkResourceAssembler implements ResourceAssembler<Drink, Resource<Drink>> {

    @Override
    public Resource<Drink> toResource(Drink drink) {
        return new Resource<>(drink,
                linkDrink(drink).withSelfRel(),
                linkDrinkList().withRel("drinks"));
    }

    public ControllerLinkBuilder linkDrinkList() {
        return linkTo(methodOn(DrinkController.class).getAllDrinks());
    }

    public ControllerLinkBuilder linkDrink(Drink drink) {
        return linkTo(methodOn(DrinkController.class).getOneDrink(drink.getId()));
    }
}
