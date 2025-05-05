package com.example.demo.factory;

import com.example.demo.dto.common.CardFormType;
import com.example.demo.dto.common.CardStatus;
import com.example.demo.dto.requests.OrderCardRequest;
import com.example.demo.entity.Card;
import com.example.demo.utils.CardIssuingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class CardFactory {

    private final CardIssuingUtils cardIssuingUtils;

    public Card getCard(
            OrderCardRequest orderCardRequest) {

        return Card.builder()
                .token("9831029301")
                .cardStatus(CardStatus.ISSUED)
                .cardFormType(orderCardRequest.cardFormType())
                .embossedName(cardIssuingUtils.getEmbossedName(
                        orderCardRequest.firstname(),
                        orderCardRequest.surname()
                ))
                .maskedPan("**** **** **** 1234")
                .expiryDate(cardIssuingUtils.getExpiryDate())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public Card getCard() {
        return Card.builder()
                .token("9831029301")
                .cardStatus(CardStatus.ISSUED)
                .cardFormType(CardFormType.VIRTUAL)
                .embossedName("Some One")
                .maskedPan("**** **** **** 0000")
                .expiryDate(cardIssuingUtils.getExpiryDate())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }
}
