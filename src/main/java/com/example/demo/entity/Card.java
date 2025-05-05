package com.example.demo.entity;

import com.example.demo.dto.common.CardFormType;
import com.example.demo.dto.common.CardStatus;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "cards")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Card {

    @Id
    @Tsid
    @Column(name = "id")
    private Long id;

    @Column(name = "token", nullable = false, length = 30)
    private String token;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "card_status", length = 16, nullable = false)
    private CardStatus cardStatus = CardStatus.ISSUED;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_form_type", length = 10, nullable = false)
    private CardFormType cardFormType;

    @Column(name = "embossed_name", length = 50, nullable = false)
    private String embossedName;

    @Column(name = "masked_pan", length = 20, nullable = false)
    private String maskedPan;

    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Version
    @Column(name = "version", nullable = false)
    private short version;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @Column(name = "deleted_reason")
    private String deletedReason;
}
