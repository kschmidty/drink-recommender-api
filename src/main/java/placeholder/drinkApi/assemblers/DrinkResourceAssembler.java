package placeholder.drinkapi.assemblers;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import placeholder.drinkapi.controllers.DrinkController;
import placeholder.drinkapi.models.Drink;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class DrinkResourceAssembler implements ResourceAssembler<Drink, Resource<Drink>> {

    @Override
    public Resource<Drink> toResource(Drink drink) {

        return new Resource<>(drink,
                linkTo(methodOn(DrinkController.class).one(drink.getId())).withSelfRel(),
                linkTo(methodOn(DrinkController.class).all()).withRel("drinks"));
    }
}
