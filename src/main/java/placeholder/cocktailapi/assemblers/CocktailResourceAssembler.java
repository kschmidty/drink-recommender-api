package placeholder.cocktailapi.assemblers;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import placeholder.cocktailapi.controllers.CocktailController;
import placeholder.cocktailapi.models.Cocktail;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CocktailResourceAssembler implements ResourceAssembler<Cocktail, Resource<Cocktail>> {

    @Override
    public Resource<Cocktail> toResource(Cocktail cocktail) {

        return new Resource<>(cocktail,
                linkTo(methodOn(CocktailController.class).one(cocktail.getId())).withSelfRel(),
                linkTo(methodOn(CocktailController.class).all()).withRel("cocktails"));
    }
}
