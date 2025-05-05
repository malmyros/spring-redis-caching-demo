package com.example.demo.factory;

import com.example.demo.dto.responses.CardResponse;
import com.example.demo.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardResponseFactory {

    public CardResponse getCardResponse(
            Card card) {

        return CardResponse.builder()
                .id(card.getId())
                .token(card.getToken())
                .cardStatus(card.getCardStatus())
                .cardFormType(card.getCardFormType())
                .embossedName(card.getEmbossedName())
                .maskedPan(card.getMaskedPan())
                .expiryDate(card.getExpiryDate())
                .createdAt(card.getCreatedAt())
                .updatedAt(card.getUpdatedAt())
                .build();
    }
}
