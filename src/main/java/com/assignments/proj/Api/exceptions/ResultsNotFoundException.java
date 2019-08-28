package com.assignments.proj.Api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to be thrown when there no data found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResultsNotFoundException extends RuntimeException{

    public ResultsNotFoundException(String message) {
        super(message);
    }

}
