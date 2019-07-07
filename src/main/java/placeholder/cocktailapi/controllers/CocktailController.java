package placeholder.cocktailapi.controllers;

import org.springframework.web.bind.annotation.*;
import placeholder.cocktailapi.daos.CocktailRepository;
import placeholder.cocktailapi.exceptions.CocktailNotFoundException;
import placeholder.cocktailapi.models.Cocktail;

import java.util.List;

@RestController
@RequestMapping("/cocktail")
public class CocktailController {

    private final CocktailRepository repository;

    CocktailController(CocktailRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping()
    List<Cocktail> all() {
        return repository.findAll();
    }

    @PostMapping()
    Cocktail newCocktail(@RequestBody Cocktail newCocktail) {
        return repository.save(newCocktail);
    }

    // Single item

    @GetMapping("/{id}")
    Cocktail one(@PathVariable String id) {

        return repository.findById(id)
                .orElseThrow(() -> new CocktailNotFoundException(id));
    }

    @PutMapping("/{id}")
    Cocktail replaceCocktail(@RequestBody Cocktail newCocktail, @PathVariable String id) {

        return repository.findById(id)
                .map(cocktail -> {
                    cocktail.setName(newCocktail.getName());
                    return repository.save(cocktail);
                })
                .orElseGet(() -> {
                    newCocktail.setId(id);
                    return repository.save(newCocktail);
                });
    }

    @DeleteMapping("/{id}")
    void deleteCocktail(@PathVariable String id) {
        repository.deleteById(id);
    }
}
