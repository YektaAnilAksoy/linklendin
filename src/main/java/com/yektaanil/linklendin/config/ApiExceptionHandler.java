package com.yektaanil.linklendin.config;


import com.yektaanil.linklendin.dto.exception.ExceptionDTO;
import com.yektaanil.linklendin.exception.UserAlreadyTakenException;
import java.util.Date;
import javax.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author : Yekta Anil AKSOY
 * @since : 17.10.2021
 **/
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ExceptionDTO> handleBadCredentialsException(
            BadCredentialsException ex, WebRequest request) {
        ExceptionDTO errorDetails =
                new ExceptionDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyTakenException.class)
    public final ResponseEntity<ExceptionDTO> handleUsernameAlreadyTakenException(
            UserAlreadyTakenException ex, WebRequest request) {
        ExceptionDTO errorDetails =
                new ExceptionDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<ExceptionDTO> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {
        ExceptionDTO errorDetails =
                new ExceptionDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ExceptionDTO> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest request) {
        ExceptionDTO errorDetails =
                new ExceptionDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ExceptionDTO> handleAllException(Exception ex, WebRequest request) {
        ExceptionDTO errorDetails =
                new ExceptionDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
