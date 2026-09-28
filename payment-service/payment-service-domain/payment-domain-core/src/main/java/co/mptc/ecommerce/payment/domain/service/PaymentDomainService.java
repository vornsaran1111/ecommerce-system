package co.mptc.ecommerce.payment.domain.service;

import co.mptc.ecommerce.payment.domain.entity.CreditEntry;
import co.mptc.ecommerce.payment.domain.entity.CreditHistory;
import co.mptc.ecommerce.payment.domain.entity.Payment;
import co.mptc.ecommerce.order.domain.valueObject.PaymentStatus;

public interface PaymentDomainService {

    CreditHistory validateAndInitiatePayment(Payment payment, CreditEntry creditEntry);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);

}
