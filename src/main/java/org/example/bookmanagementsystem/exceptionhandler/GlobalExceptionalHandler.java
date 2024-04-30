package org.example.bookmanagementsystem.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionalHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class )
    public ResponseEntity<ErrorResponse>  handleCustomException(CustomException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(errorResponse);

    }
}
