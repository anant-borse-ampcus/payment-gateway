package com.payment_gateway.clone.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        Payment payment = paymentMapper.toEntity(paymentRequestDto);
        Payment saved = paymentRepository.save(payment);
        log.info("Payment created  with id : {}", saved.getId());
        return paymentMapper.toResponse(saved);
    }

    @Override
    public PaymentResponseDto getPayment(UUID paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found with id: " + paymentId)
                );
        return paymentMapper.toResponse(payment);
    }

    @Override
    public List<PaymentResponseDto> getTransactionHistory(UUID merchantId) {
        List<Payment> payment = paymentRepository.findByMerchantId(merchantId);
        return paymentMapper.toResponseList(payment);
    }
}
