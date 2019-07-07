package placeholder.cocktailapi.exceptions;

public class CocktailNotFoundException extends RuntimeException {

    public CocktailNotFoundException(String id) {
        super("Could not find cocktail " + id);
    }
}
