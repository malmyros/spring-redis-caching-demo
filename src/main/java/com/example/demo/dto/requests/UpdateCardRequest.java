package com.example.demo.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UpdateCardRequest(

        @NotBlank(message = "The embossed name cannot be blank")
        @Size(min = 1, max = 50, message = "The embossed name needs to be between 1 and 50 characters")
        @Schema(description = "The customer's stamped or raised name on the card",
                example = "MR JOHN DOE")
        String embossedName
) {
}
