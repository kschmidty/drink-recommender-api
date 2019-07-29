package com.drink.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "drinks")
public class Drink {

    @Id
    public String id;

    public String name;

    public List<String> ingredients;

    public Drink(String name) {
        this.name = name;
    }
}
