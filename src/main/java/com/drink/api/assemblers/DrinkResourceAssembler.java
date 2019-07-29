package com.drink.api.assemblers;

import com.drink.api.models.Drink;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import com.drink.api.controllers.DrinkController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class DrinkResourceAssembler implements ResourceAssembler<Drink, Resource<Drink>> {

    @Override
    public Resource<Drink> toResource(Drink drink) {

        return new Resource<>(drink,
                linkTo(methodOn(DrinkController.class).one(drink.get_id())).withSelfRel(),
                linkTo(methodOn(DrinkController.class).all()).withRel("drinks"));
    }
}
