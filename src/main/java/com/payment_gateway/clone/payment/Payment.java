package com.payment_gateway.clone.payment;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false,name = "idempotency_key")
    private String idempotencyKey;

    @Column(nullable = false,name = "merchant_id")
    private UUID merchantId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency = "INR";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,columnDefinition = "payment_status")
    PaymentStatus status = PaymentStatus.PENDING;

    private String description;

    @Column(columnDefinition = "jsonb")
    private String metadata;

    @Column(nullable = false,name = "created_at",updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false,name = "updated_at")
    private LocalDateTime lastUpdatedAt = LocalDateTime.now();

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.lastUpdatedAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.lastUpdatedAt = LocalDateTime.now();
    }
}
