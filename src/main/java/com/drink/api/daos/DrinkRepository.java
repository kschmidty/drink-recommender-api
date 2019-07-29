package com.drink.api.daos;

import com.drink.api.models.Drink;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DrinkRepository extends MongoRepository<Drink, String> {

    Optional<Drink> findBy_id(ObjectId _id);
    void deleteBy_id(ObjectId _id);
}
