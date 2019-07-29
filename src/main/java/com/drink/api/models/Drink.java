package com.drink.api.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "drinks")
public class Drink {

    @Id
    public ObjectId _id;

    public String name;

    public Drink(String name) {
        this.name = name;
    }
}
