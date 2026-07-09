package com.payment_gateway.clone.payment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "metadata", ignore = true)
    Payment toEntity(PaymentRequestDto request);

    PaymentResponseDto toResponse(Payment payment);
    List<PaymentResponseDto> toResponseList(List<Payment> payments);
}
