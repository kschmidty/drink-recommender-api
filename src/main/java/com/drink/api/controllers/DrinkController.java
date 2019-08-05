package com.drink.api.controllers;

import com.drink.api.assemblers.DrinkResourceAssembler;
import com.drink.api.repo.DrinkRepository;
import com.drink.api.exceptions.DrinkNotFoundException;
import com.drink.api.domain.Drink;
import com.drink.api.services.DrinkServices;
import com.drink.api.services.DrinkServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/drinks")
public class DrinkController {

    @Qualifier("DrinkServicesImpl")
    private DrinkServices drinkServices;

    @Autowired
    DrinkController(DrinkServices drinkServices) {
        this.drinkServices = drinkServices;
    }

    // Aggregate root

    @GetMapping()
    public ResponseEntity getAllDrinks() {
        return drinkServices.getAllDrinks();
    }

    @PostMapping()
    public ResponseEntity createDrink(@RequestBody Drink newDrink) {
        return drinkServices.createDrink(newDrink);
    }

    // Single item

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneDrink(@PathVariable String id) {
        return drinkServices.getOneDrink(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDrink(@PathVariable String id, @RequestBody Drink newDrink) {
        return drinkServices.updateDrink(id, newDrink);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDrink(@PathVariable String id) {
        return drinkServices.deleteDrink(id);
    }
}
