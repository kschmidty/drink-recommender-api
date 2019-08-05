package com.drink.api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "drinks")
public class Drink {

    private static final String TOO_SMALL_MESSAGE = "Please provide at least 2 ";

    @Id
    public String id;

    @NotEmpty(message = "Please provide a title")
    public String title;

    @NotEmpty
    @Size(min = 2, message = "Please include at least 2 ingredients")
    public List<String> ingredients;

    @NotEmpty
    @Size(min = 2, message = "Please include at least 2 measurements")
    public List<Measurement> measurements;

    @NotEmpty(message = "please provide instructions")
    public List<String> instructions;

    @Data
    private static class Measurement {

        @NotNull
        private double amount;

        @NotEmpty
        private String unit;

        @NotEmpty
        private String ingredient;
    }
}
