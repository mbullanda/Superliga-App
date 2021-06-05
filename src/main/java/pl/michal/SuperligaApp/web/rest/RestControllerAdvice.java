package pl.michal.SuperligaApp.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.michal.SuperligaApp.exception.MissingClubException;
import pl.michal.SuperligaApp.exception.MissingPlayerException;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler({MissingClubException.class, MissingPlayerException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String missingEntity(RuntimeException e){
        return e.getMessage();
    }
}
