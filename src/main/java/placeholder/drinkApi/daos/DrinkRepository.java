package placeholder.drinkapi.daos;

import org.springframework.data.mongodb.repository.MongoRepository;
import placeholder.drinkapi.models.Drink;

public interface DrinkRepository extends MongoRepository<Drink, String> {

}
