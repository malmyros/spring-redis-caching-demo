package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CardNotFoundException extends RuntimeException {

    public CardNotFoundException(Long cardId) {
        super("Couldn't find card " + cardId);
    }
}
