package com.mohamed.applicationbancaire.handlers;

import com.mohamed.applicationbancaire.exeptions.ObjectValidationException;
import com.mohamed.applicationbancaire.exeptions.OperationNotPermittedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectValidationException.class)
    public ResponseEntity<ExceptionRepresentation> handleValidate(ObjectValidationException exception){
        ExceptionRepresentation representation = ExceptionRepresentation
                .builder()
                .errorMessage("Object not valid exception has occurred")
                .errorSource(exception.getViolationSource())
                .validationErrors(exception.getViolations())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<ExceptionRepresentation> handlePermission(OperationNotPermittedException exception){
        ExceptionRepresentation representation = ExceptionRepresentation
                .builder()
                .errorMessage(exception.getErrorMessage())
                .errorSource(exception.getSource())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(representation);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handleEntityNotFound(EntityNotFoundException exception){
        ExceptionRepresentation representation = ExceptionRepresentation
                .builder()
                .errorMessage(exception.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(representation);
    }

    // pour catcher l'exception dans le cas de saisir un mail déja éxist
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionRepresentation> handleEmailUnique(DataIntegrityViolationException exception){
        ExceptionRepresentation representation = ExceptionRepresentation
                .builder()
                .errorMessage("Email already exists!!")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionRepresentation> handleDisableException(){
        ExceptionRepresentation representation = ExceptionRepresentation
                .builder()
                .errorMessage("You cannot access your account because it is not yet activated")
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(representation);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionRepresentation> handleBadCredentialsException(){
        ExceptionRepresentation representation = ExceptionRepresentation
                .builder()
                .errorMessage("Email or password is incorrect")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }
}
