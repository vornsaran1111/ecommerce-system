package co.mptc.ecommerce.payment.domain.dto;

import co.mptc.ecommerce.order.domain.valueObject.PaymentStatus;

import java.util.UUID;

public record CreatePaymentResult(
        UUID paymentId,
        PaymentStatus paymentStatus
) {
}
