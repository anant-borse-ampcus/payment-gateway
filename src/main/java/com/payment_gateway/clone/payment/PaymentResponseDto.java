package com.payment_gateway.clone.payment;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentResponseDto {
    private UUID id;
    private UUID merchantId;
    private BigDecimal amount;
    private String currency;
    private String description;
    private String idempotencyKey;
    private LocalDateTime createdAt;
    PaymentStatus status;
}
