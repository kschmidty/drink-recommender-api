package com.drink.api.exceptions;

import org.bson.types.ObjectId;

public class DrinkNotFoundException extends RuntimeException {

    public DrinkNotFoundException(ObjectId _id) {
        super("Could not find drink " + _id);
    }
}
