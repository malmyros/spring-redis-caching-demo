package com.example.demo.service;

import com.example.demo.entity.Card;
import com.example.demo.exception.CardNotFoundException;
import com.example.demo.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardRepositoryService {

    private final CardRepository cardRepository;

    @Cacheable(value = "cards", key = "#id")
    public Card getCardById(Long id) {
        return cardRepository
                .findCardById(id)
                .orElseThrow(() -> new CardNotFoundException(id));
    }

    @CachePut(value = "cards", key = "#card.id")
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    @CacheEvict(value = "cards", key = "#id")
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
