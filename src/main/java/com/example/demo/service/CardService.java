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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CardFactory cardFactory;
    private final CardResponseFactory cardResponseFactory;

    public CardResponse orderCard(
            OrderCardRequest orderCardRequest) {

        Card card = cardFactory.getCard(orderCardRequest);
        Card storedCard = cardRepository.save(card);
        log.info("Issued a new card {}", card.getId());

        return cardResponseFactory.getCardResponse(storedCard);
    }

    @Cacheable(value = "cards", key = "#id")
    public CardResponse getCard(Long id) {

        Card card = cardRepository
                .findCardById(id)
                .orElseThrow(() -> new CardNotFoundException(id));
        log.info("Found card {} details", id);

        return cardResponseFactory.getCardResponse(card);
    }

    @CachePut(value = "cards", key = "#id")
    public CardResponse updateCard(
            Long id,
            UpdateCardRequest updateCardRequest) {

        Card card = cardRepository
                .findCardById(id)
                .orElseThrow(() -> new CardNotFoundException(id));
        log.info("Found card {}", id);

        card.setEmbossedName(updateCardRequest.embossedName());
        card.setUpdatedAt(Instant.now());

        Card updatedCard = cardRepository.save(card);
        log.info("Updated card {} details", id);

        return cardResponseFactory.getCardResponse(updatedCard);
    }

    @CacheEvict(value = "cards", key = "#id")
    public void deleteCard(Long id) {

        cardRepository.deleteById(id);
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
