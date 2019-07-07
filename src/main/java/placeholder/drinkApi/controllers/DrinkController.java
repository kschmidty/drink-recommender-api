package placeholder.drinkApi.controllers;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import placeholder.drinkApi.assemblers.DrinkResourceAssembler;
import placeholder.drinkApi.daos.DrinkRepository;
import placeholder.drinkApi.exceptions.DrinkNotFoundException;
import placeholder.drinkApi.models.Drink;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/drinks")
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
