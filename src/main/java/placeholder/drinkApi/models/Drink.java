package placeholder.drinkapi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "drinks")
public class Drink {

    @Transient
    public static final String SEQUENCE_NAME = "drinks_sequence";

    @Id
    public String id;

    public String name;

    public Drink(String name) {
        this.name = name;
    }
}
