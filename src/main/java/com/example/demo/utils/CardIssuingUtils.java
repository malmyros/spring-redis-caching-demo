package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class CardIssuingUtils {

    public String getEmbossedName(String firstname, String surname) {
        return "%s %s".formatted(firstname, surname).toUpperCase();
    }

    public Instant getExpiryDate() {

        int totalYears = 3;
        int totalDays = 365;
        int amountToAdd = totalYears * totalDays;

        Instant now = Instant.now();
        return now.plus(amountToAdd, ChronoUnit.DAYS);
    }
}
