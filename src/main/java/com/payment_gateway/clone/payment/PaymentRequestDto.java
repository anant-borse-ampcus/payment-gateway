package com.payment_gateway.clone.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentRequestDto {
    @NotBlank
    private String idempotencyKey;
    @NotNull
    private UUID merchantId;
    private String currency = "INR";
    private String description;
    @NotNull
    private BigDecimal amount;
}
