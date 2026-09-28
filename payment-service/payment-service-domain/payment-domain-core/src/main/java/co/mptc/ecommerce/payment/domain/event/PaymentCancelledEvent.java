package co.mptc.ecommerce.payment.domain.event;

import co.mptc.ecommerce.payment.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.List;

public class PaymentCancelledEvent extends PaymentEvent {

    public PaymentCancelledEvent(Payment payment, ZonedDateTime createdAt) {
        super(payment, createdAt, List.of());
    }
}
