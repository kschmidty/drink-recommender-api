package placeholder.cocktailapi.daos;

import org.springframework.data.mongodb.repository.MongoRepository;
import placeholder.cocktailapi.models.Cocktail;

public interface CocktailRepository extends MongoRepository<Cocktail, String> {

}
