package com.drink.api.services;

import com.drink.api.domain.Drink;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DrinkServices {

    ResponseEntity getAllDrinks();
    ResponseEntity createDrink(Drink newDrink);
    ResponseEntity getOneDrink(String id);
    ResponseEntity updateDrink(String id, Drink newDrink);
    ResponseEntity deleteDrink(String id);
}
