package placeholder.drinkApi.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
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
