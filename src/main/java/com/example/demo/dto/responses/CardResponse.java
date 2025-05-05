package com.example.demo.dto.responses;

import com.example.demo.dto.common.CardFormType;
import com.example.demo.dto.common.CardStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;
import java.time.Instant;

@Builder
public record CardResponse(

        @NotNull
        @Schema(description = "The card's unique id")
        Long id,

        @NotBlank
        @Schema(description = "The public token of the card",
                example = "092131737")
        String token,

        @NotNull
        @Schema(description = "The status of the card",
                example = "ACTIVE")
        CardStatus cardStatus,

        @NotNull
        @Schema(description = "The form of the card to identify whether it's physical or virtual",
                example = "VIRTUAL")
        CardFormType cardFormType,

        @NotBlank
        @Schema(description = "The customer's stamped or raised name on the card",
                example = "MR JOHN DOE")
        String embossedName,

        @NotBlank
        @Schema(description = "The card's masked PAN (primary account number)",
                example = "**** **** **** 1234")
        String maskedPan,

        @NotNull
        @Schema(description = "The date time the card is due to expire")
        Instant expiryDate,

        @NotNull
        @Schema(description = "The date time the card was issued")
        Instant createdAt,

        @NotNull
        @Schema(description = "The date time the card was updated")
        Instant updatedAt
) implements Serializable {
}
