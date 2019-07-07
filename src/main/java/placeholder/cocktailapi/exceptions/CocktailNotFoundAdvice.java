package placeholder.cocktailapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CocktailNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CocktailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String CocktailNotFoundHandler(CocktailNotFoundException ex) {
        return ex.getMessage();
    }
}
