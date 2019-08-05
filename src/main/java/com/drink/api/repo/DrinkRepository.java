package com.drink.api.repo;

import com.drink.api.domain.Drink;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DrinkRepository extends MongoRepository<Drink, String> {

}
