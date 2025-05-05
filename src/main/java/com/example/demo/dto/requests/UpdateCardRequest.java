package com.example.demo.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UpdateCardRequest(

        @NotBlank
        @Size(min = 1, max = 15)
        @Schema(description = "The customer's stamped or raised name on the card",
                example = "MR JOHN DOE")
        String embossedName
) {
}
