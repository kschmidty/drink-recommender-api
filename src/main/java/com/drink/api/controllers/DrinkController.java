package com.drink.api.controllers;

import com.drink.api.domain.Drink;
import com.drink.api.response.DrinkResponseFactory;
import com.drink.api.services.DrinkServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drinks")
public class DrinkController {

    private DrinkServices drinkServices;

    @Autowired
    DrinkController(DrinkServices drinkServices) {
        this.drinkServices = drinkServices;
    }

    // Aggregate root

    @GetMapping()
    public ResponseEntity getAllDrinks() {
        return DrinkResponseFactory.ok(drinkServices.getAllDrinks());
    }

    @PostMapping()
    public ResponseEntity createDrink(@RequestBody Drink newDrink) {
        return DrinkResponseFactory.created(drinkServices.createDrink(newDrink));
    }

    // Single item

    @GetMapping("/{id}")
    public ResponseEntity getOneDrink(@PathVariable String id) {
        return DrinkResponseFactory.ok(drinkServices.getOneDrink(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateDrink(@PathVariable String id, @RequestBody Drink newDrink) {
        return DrinkResponseFactory.ok(drinkServices.updateDrink(id, newDrink));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDrink(@PathVariable String id) {
        drinkServices.deleteDrink(id);
        return DrinkResponseFactory.noContent();
    }
}
