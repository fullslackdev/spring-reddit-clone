package dev.fullslack.springredditclone.controller;

import dev.fullslack.springredditclone.exception.ItemNotFoundException;
import dev.fullslack.springredditclone.exception.SpringRedditException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> handleNoSuchElementFoundException(ItemNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(SpringRedditException.class)
    public ResponseEntity<String> handleSpringRedditException(SpringRedditException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
