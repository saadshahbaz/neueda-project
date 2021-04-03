package ca.mcgill.ecse321.arms.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public ResponseEntity<Object> handleInvalidFieldException(IllegalArgumentException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(e, badRequest);
    }
}
