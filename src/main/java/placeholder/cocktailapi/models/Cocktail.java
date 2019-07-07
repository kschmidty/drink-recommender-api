package placeholder.cocktailapi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cocktails")
public class Cocktail {

    @Transient
    public static final String SEQUENCE_NAME = "cocktails_sequence";

    @Id
    public String id;

    public String name;

    public Cocktail(String name) {
        this.name = name;
    }
}
