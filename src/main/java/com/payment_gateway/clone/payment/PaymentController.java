package com.payment_gateway.clone.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create-payment")
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody @Valid PaymentRequestDto paymentRequestDto) {
        return ResponseEntity.status(201).body(paymentService.createPayment(paymentRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> getPaymentById(@PathVariable UUID id){
        return ResponseEntity.ok(paymentService.getPayment(id) );
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getTransactionHistory(@RequestParam  UUID merchantId){
        return ResponseEntity.status(200).body(paymentService.getTransactionHistory(merchantId));
    }
}
