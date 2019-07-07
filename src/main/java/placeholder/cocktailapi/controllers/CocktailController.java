package placeholder.cocktailapi.controllers;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;
import placeholder.cocktailapi.assemblers.CocktailResourceAssembler;
import placeholder.cocktailapi.daos.CocktailRepository;
import placeholder.cocktailapi.exceptions.CocktailNotFoundException;
import placeholder.cocktailapi.models.Cocktail;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/cocktail")
public class CocktailController {

    private final CocktailRepository repository;
    private final CocktailResourceAssembler assembler;

    CocktailController(CocktailRepository repository,
                       CocktailResourceAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping()
    public Resources<Resource<Cocktail>> all() {

        List<Resource<Cocktail>> cocktails = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(cocktails,
                linkTo(methodOn(CocktailController.class).all()).withSelfRel());
    }

    @PostMapping()
    public Resource<Cocktail> newCocktail(@RequestBody Cocktail newCocktail) {

        Cocktail cocktail = repository.save(newCocktail);

        return assembler.toResource(cocktail);
    }

    // Single item

    @GetMapping("/{id}")
    public Resource<Cocktail> one(@PathVariable String id) {

        Cocktail cocktail = repository.findById(id)
                .orElseThrow(() -> new CocktailNotFoundException(id));

        return assembler.toResource(cocktail);
    }

    @PutMapping("/{id}")
    public Resource<Cocktail> replaceCocktail(@RequestBody Cocktail newCocktail, @PathVariable String id) {

        return repository.findById(id)
                .map(cocktail -> {
                    cocktail.setName(newCocktail.getName());
                    return assembler.toResource(repository.save(cocktail));
                })
                .orElseGet(() -> {
                    newCocktail.setId(id);
                    return assembler.toResource(repository.save(newCocktail));
                });
    }

    @DeleteMapping("/{id}")
    public void deleteCocktail(@PathVariable String id) {
        repository.deleteById(id);
    }
}
