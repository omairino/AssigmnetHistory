package com.assignments.proj.Api.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

/**
 * Exception handler class
 */
@ControllerAdvice
public class AssignmentServiceAdvice {

    /**
     * handles NullPointerException, SQLException, thrown exceptions
     * by returning internal server error status
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(
            {SQLException.class, NullPointerException.class})
    public void handle() {}


}
