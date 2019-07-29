package com.drink.api.daos;

import com.drink.api.models.Drink;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DrinkRepository extends MongoRepository<Drink, String> {

}
