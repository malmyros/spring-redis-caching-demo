package com.example.demo.controller;

import com.example.demo.dto.requests.OrderCardRequest;
import com.example.demo.dto.requests.UpdateCardRequest;
import com.example.demo.dto.responses.CardResponse;
import com.example.demo.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
@Tag(name = "Card", description = "Card Controller")
public class CardController {

    private final CardService cardService;

    @Operation(summary = "Order a card for the customer")
    @PostMapping
    public ResponseEntity<CardResponse> orderCard(
            @Valid @RequestBody OrderCardRequest orderCardRequest) {

        log.info("Received request to order a card");
        CardResponse cardResponse = cardService.orderCard(orderCardRequest);
        return ResponseEntity.ok(cardResponse);
    }

    @Operation(summary = "Retrieve the details of a card by a card id lookup")
    @GetMapping(value = "/{cardId}")
    public ResponseEntity<CardResponse> getCard(
            @PathVariable(name = "cardId") Long cardId) {

        log.info("Received request to retrieve details for card {}", cardId);
        CardResponse cardResponse = cardService.getCard(cardId);
        return ResponseEntity.ok(cardResponse);
    }

    @Operation(summary = "Update the card's embossed name")
    @PutMapping(value = "/{cardId}")
    public ResponseEntity<CardResponse> updateCard(
            @PathVariable(name = "cardId") Long cardId,
            @Valid @RequestBody UpdateCardRequest updateCardRequest) {

        log.info("Received request to update embossed name for card {}", cardId);
        CardResponse cardResponse = cardService.updateCard(cardId, updateCardRequest);
        return ResponseEntity.ok(cardResponse);
    }

    @Operation(summary = "Delete the customer's card")
    @DeleteMapping(value = "/{cardId}")
    public ResponseEntity<Void> deleteCard(
            @PathVariable(name = "cardId") Long cardId) {

        log.info("Received request to delete card {}", cardId);
        cardService.deleteCard(cardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Generate cards to test performance")
    @PostMapping(value = "/generate-cards")
    public ResponseEntity<Void> generateCards() throws InterruptedException {

        log.info("Received request to generate cards");
        cardService.generateCards();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
