package placeholder.drinkapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DrinkNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(DrinkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String DrinkNotFoundHandler(DrinkNotFoundException ex) {
        return ex.getMessage();
    }
}
