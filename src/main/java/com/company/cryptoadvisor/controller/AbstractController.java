package com.company.cryptoadvisor.controller;

import com.company.cryptoadvisor.model.Currency;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractController {

    public ResponseEntity<Object> generateResponse(String message) {
        return ResponseEntity.ok().body(ResponseMessage.builder()
                .messege(message)
                .build());
    }

    public ResponseEntity<ResponseMessage> generateResponse(String message, Currency currency) {
        return ResponseEntity.ok().body(ResponseMessage.builder()
                .messege(message)
                .responce(currency)
                .build());
    }

    public ResponseEntity<ResponseMessage> generateNotFoundResponse(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseMessage.builder()
                .messege(message)
                .build());
    }
    public ResponseEntity<Object> generateBadResponse(String message) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(message);
    }

    public ResponseEntity<ResponseMessage> getResponseMessageResponseEntity(Currency currency, String symbol) {
        return currency != null ?
                generateResponse("", currency) :
                generateNotFoundResponse("Not fount records. Symbol " + symbol);
    }
}
