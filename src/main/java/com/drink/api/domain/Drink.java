package com.drink.api.domain;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "drinks")
public class Drink {

  @Id
  private String id;

  @NotEmpty(message = "Please provide a title")
  private String title;

  @NotEmpty
  @Size(min = 2, message = "Please include at least 2 ingredients")
  private List<String> ingredients;

  @NotEmpty
  @Size(min = 2, message = "Please include at least 2 measurements")
  private List<Measurement> measurements;

  @NotEmpty(message = "please provide instructions")
  private List<String> instructions;

  @Data
  private static class Measurement {

    @NotNull
    private double amount;

    @NotEmpty
    private String unit;

    @NotEmpty
    private String ingredient;
  }

  public void setProperties(Drink drink) {

    this.title = drink.getTitle();
    this.ingredients = drink.getIngredients();
    this.measurements = drink.getMeasurements();
    this.instructions = drink.getInstructions();
  }
}
