package com.drink.api.controllers;

import com.drink.api.assemblers.DrinkResourceAssembler;
import com.drink.api.domain.Drink;
import com.drink.api.response.DrinkResponseFactory;
import com.drink.api.services.DrinkServices;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drinks")
public class DrinkController {

  @Autowired
  private DrinkServices drinkServices;
  @Autowired
  private DrinkResourceAssembler assembler;

  // Aggregate root

  @GetMapping()
  public ResponseEntity getAllDrinks() {
    return DrinkResponseFactory.ok(drinkServices.getAllDrinks(), assembler);
  }

  @PostMapping()
  public ResponseEntity createDrink(@Valid @RequestBody Drink newDrink) {
    return DrinkResponseFactory.created(drinkServices.createDrink(newDrink), assembler);
  }

  // Single item

  @GetMapping("/{id}")
  public ResponseEntity getOneDrink(@PathVariable String id) {
    return DrinkResponseFactory.ok(drinkServices.getOneDrink(id), assembler);
  }

  @PutMapping("/{id}")
  public ResponseEntity updateDrink(@PathVariable String id, @Valid @RequestBody Drink newDrink) {
    return DrinkResponseFactory.ok(drinkServices.updateDrink(id, newDrink), assembler);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteDrink(@PathVariable String id) {
    drinkServices.deleteDrink(id);
    return DrinkResponseFactory.noContent();
  }
}
