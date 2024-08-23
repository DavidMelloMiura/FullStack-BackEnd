package com.david.helpdesk.resources.exceptions;

import com.david.helpdesk.services.exception.DataIntegrityViolationException;
import com.david.helpdesk.services.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(
            ObjectNotFoundException ex, HttpServletRequest request) {

        StandardError error = new StandardError(
                System.currentTimeMillis(), //timestamp
                HttpStatus.NOT_FOUND.value(), //status
                "Object Not Found", //error
                ex.getMessage(), //message
                request.getRequestURI()); //path
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(
            DataIntegrityViolationException ex, HttpServletRequest request) {

        StandardError error = new StandardError(
                System.currentTimeMillis(), //timestamp
                HttpStatus.BAD_REQUEST.value(), //status
                "Violação de dados", //error
                ex.getMessage(), //message
                request.getRequestURI()); //path
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationErrors(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        ValidationError erros = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Erro na validação dos campos", ex.getMessage(), request.getRequestURI());

        for(FieldError x : ex.getBindingResult().getFieldErrors()) {
            erros.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

}
