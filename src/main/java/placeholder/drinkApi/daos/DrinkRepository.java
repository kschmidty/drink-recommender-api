package placeholder.drinkApi.daos;

import org.springframework.data.mongodb.repository.MongoRepository;
import placeholder.drinkApi.models.Drink;

public interface DrinkRepository extends MongoRepository<Drink, String> {

}
