package placeholder.drinkapi.controllers;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;
import placeholder.drinkapi.assemblers.DrinkResourceAssembler;
import placeholder.drinkapi.daos.DrinkRepository;
import placeholder.drinkapi.exceptions.DrinkNotFoundException;
import placeholder.drinkapi.models.Drink;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/drink")
public class DrinkController {

    private final DrinkRepository repository;
    private final DrinkResourceAssembler assembler;

    DrinkController(DrinkRepository repository,
                       DrinkResourceAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping()
    public Resources<Resource<Drink>> all() {

        List<Resource<Drink>> drinks = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(drinks,
                linkTo(methodOn(DrinkController.class).all()).withSelfRel());
    }

    @PostMapping()
    public Resource<Drink> newDrink(@RequestBody Drink newDrink) {

        Drink drink = repository.save(newDrink);

        return assembler.toResource(drink);
    }

    // Single item

    @GetMapping("/{id}")
    public Resource<Drink> one(@PathVariable String id) {

        Drink drink = repository.findById(id)
                .orElseThrow(() -> new DrinkNotFoundException(id));

        return assembler.toResource(drink);
    }

    @PutMapping("/{id}")
    public Resource<Drink> replaceDrink(@RequestBody Drink newDrink, @PathVariable String id) {

        return repository.findById(id)
                .map(drink -> {
                    drink.setName(newDrink.getName());
                    return assembler.toResource(repository.save(drink));
                })
                .orElseGet(() -> {
                    newDrink.setId(id);
                    return assembler.toResource(repository.save(newDrink));
                });
    }

    @DeleteMapping("/{id}")
    public void deleteDrink(@PathVariable String id) {
        repository.deleteById(id);
    }
}
