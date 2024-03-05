package no.knowit.integration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerAdvisor {

    Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElementException(NoSuchElementException e) {
        String errorMessage = "Resource not found: " + e.getMessage();

        logger.warn(errorMessage);

        return errorMessage;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        String errorMessage = "An unknown error occurred!";
        logger.error(errorMessage, e);

        return errorMessage;
    }
}