package com.example.demo.repository;

import com.example.demo.entity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CardRepository extends CrudRepository<Card, Long> {

    Optional<Card> findCardById(Long cardId);
}
