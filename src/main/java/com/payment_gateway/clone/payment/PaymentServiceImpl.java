package com.payment_gateway.clone.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        Payment payment = paymentMapper.toEntity(paymentRequestDto);
        Payment saved = paymentRepository.save(payment);
        log.info("Payment created  with id : {}", saved.getId());
        return paymentMapper.toResponse(payment);
    }
}
