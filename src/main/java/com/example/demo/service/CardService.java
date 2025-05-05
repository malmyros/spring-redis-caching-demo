package com.example.demo.service;

import com.example.demo.dto.requests.OrderCardRequest;
import com.example.demo.dto.requests.UpdateCardRequest;
import com.example.demo.dto.responses.CardResponse;
import com.example.demo.entity.Card;
import com.example.demo.exception.CardNotFoundException;
import com.example.demo.factory.CardFactory;
import com.example.demo.factory.CardResponseFactory;
import com.example.demo.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

    private final CardFactory cardFactory;
    private final CardResponseFactory cardResponseFactory;
    private final CardRepositoryService cardRepositoryService;
    private final CardRepository cardRepository;

    public CardResponse orderCard(
            OrderCardRequest orderCardRequest) {

        Card cardToStore = cardFactory.getCard(orderCardRequest);
        Card card = cardRepositoryService.saveCard(cardToStore);

        log.info("Issued a new card {}", card.getId());
        return cardResponseFactory.getCardResponse(card);
    }

    public CardResponse getCard(Long id) {

        Card card = cardRepository
                .findCardById(id)
                .orElseThrow(() -> new CardNotFoundException(id));
        log.info("Found card {} details", id);

        return cardResponseFactory.getCardResponse(card);
    }

    public CardResponse updateCard(
            Long id,
            UpdateCardRequest updateCardRequest) {

        Card cardToUpdate = cardRepositoryService.getCardById(id);
        log.info("Found card {}", id);

        cardToUpdate.setEmbossedName(updateCardRequest.embossedName());
        cardToUpdate.setUpdatedAt(Instant.now());

        Card updatedCard = cardRepositoryService.saveCard(cardToUpdate);
        log.info("Updated card {} details", id);

        return cardResponseFactory.getCardResponse(updatedCard);
    }

    public void deleteCard(Long id) {
        cardRepositoryService.deleteCard(id);
        log.info("Deleted card {}", id);
    }

    public void generateCards() {

        List<Card> cards = IntStream.range(0, 10_000)
                .asLongStream()
                .mapToObj(l -> cardFactory.getCard())
                .toList();

        cardRepository.saveAll(cards);
        log.info("Stored {} new cards", cards.size());
    }
}
