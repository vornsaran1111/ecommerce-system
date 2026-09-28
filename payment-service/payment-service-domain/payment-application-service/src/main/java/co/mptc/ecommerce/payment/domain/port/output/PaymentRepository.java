package co.mptc.ecommerce.payment.domain.port.output;

import co.mptc.ecommerce.payment.domain.entity.Payment;

public interface PaymentRepository {
    Payment savePayment(Payment payment);
}
