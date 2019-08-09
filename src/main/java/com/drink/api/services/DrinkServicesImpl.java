package com.drink.api.services;

import com.drink.api.domain.Drink;
import com.drink.api.exceptions.DrinkNotFoundException;
import com.drink.api.repo.DrinkRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrinkServicesImpl implements DrinkServices {

  @Autowired
  private DrinkRepository repository;

  @Override
  public List<Drink> getAllDrinks() {
    return repository.findAll();
  }

  @Override
  public Drink createDrink(Drink newDrink) {
    return repository.save(newDrink);
  }

  @Override
  public Drink getOneDrink(String id) {
    return repository.findById(id)
            .orElseThrow(() -> new DrinkNotFoundException(id));
  }

  @Override
  public Drink updateDrink(String id, Drink newDrink) {
    return repository.findById(id)
            .map(drink -> {
              drink.setProperties(newDrink);
              return repository.save(drink);
            })
            .orElseThrow(() -> new DrinkNotFoundException(id));
  }

  @Override
  public void deleteDrink(String id) {
    repository.findById(id).orElseThrow(() -> new DrinkNotFoundException(id));
    repository.deleteById(id);
  }
}
