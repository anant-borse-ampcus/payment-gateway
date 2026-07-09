package com.payment_gateway.clone.payment;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto);
    PaymentResponseDto getPayment (UUID paymentId);

    List<PaymentResponseDto> getTransactionHistory(UUID merchantId);
}
