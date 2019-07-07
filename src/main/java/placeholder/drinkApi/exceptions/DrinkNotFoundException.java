package placeholder.drinkapi.exceptions;

public class DrinkNotFoundException extends RuntimeException {

    public DrinkNotFoundException(String id) {
        super("Could not find drink " + id);
    }
}
