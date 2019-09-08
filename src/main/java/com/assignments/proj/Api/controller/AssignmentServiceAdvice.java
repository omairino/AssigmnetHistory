package com.assignments.proj.Api.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


//...

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class AssignmentServiceAdvice extends AbstractErrorController {

    public AssignmentServiceAdvice(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                false);
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(body, status);
    }}


