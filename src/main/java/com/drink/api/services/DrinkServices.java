package com.drink.api.services;

import com.drink.api.domain.Drink;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DrinkServices {

    List<Drink> getAllDrinks();
    Drink createDrink(Drink newDrink);
    Drink getOneDrink(String id);
    Drink updateDrink(String id, Drink newDrink);
    void deleteDrink(String id);
}
