package com.assignments.proj.Api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

/**
 * Exception handler class
 */
@RestControllerAdvice
public class ServiceAdvice extends ResponseEntityExceptionHandler {
    //NOTE: could intercept missing arguments exception or other default spring exception
    // because the framework throw an exception stating that these exception are already handled
    /**
     * handles NullPointerException, SQLException, thrown exceptions
     * by returning internal server error status
     */
    @ExceptionHandler({SQLException.class, NullPointerException.class})
    public ResponseEntity<Object> handleInternalErrors(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "there was a problem in the server ";
        if (ex.getMessage() != null) // in case of an exception thrown by the system that doesn't have a message
            bodyOfResponse = ex.getMessage();

       return handleExceptionInternal(ex, bodyOfResponse,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    // If needed uncomment...
    /**
     * handles BadRequestException, MissingServletRequestParameterException, thrown exceptions
     * by returning BAD_REQUEST error status
     */

    /*@ExceptionHandler({BadRequestException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<Object> handleMissingArguments(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(),new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }*/

    /**
     * handles ResultsNotFoundException, thrown exceptions
     * by returning NOT_FOUND error status
     */
    /*@ExceptionHandler({ResultsNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundExceptions(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(),new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }*/
}
