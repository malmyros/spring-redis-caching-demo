package com.example.demo.dto.requests;

import com.example.demo.dto.common.CardFormType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderCardRequest(

        @NotBlank(message = "The firstname cannot be blank")
        @Schema(description = "The customer's first name to use when computing the card's embossed name",
                example = "John")
        String firstname,

        @NotBlank(message = "The surname cannot be blank")
        @Schema(description = "The customer's surname to use when computing the card's embossed name",
                example = "Doe")
        String surname,

        @NotNull(message = "The card form type cannot be null")
        @Schema(description = "The for the card to issue",
                example = "VIRTUAL")
        CardFormType cardFormType
) {
}
