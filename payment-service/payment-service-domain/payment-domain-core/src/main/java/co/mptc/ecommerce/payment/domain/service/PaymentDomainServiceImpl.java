package co.mptc.ecommerce.payment.domain.service;

import co.mptc.ecommerce.order.domain.valueObject.CreditHistoryId;
import co.mptc.ecommerce.order.domain.valueObject.TransactionType;
import co.mptc.ecommerce.payment.domain.entity.CreditEntry;
import co.mptc.ecommerce.payment.domain.entity.CreditHistory;
import co.mptc.ecommerce.payment.domain.entity.Payment;
import co.mptc.ecommerce.order.domain.valueObject.PaymentStatus;

import java.util.UUID;

public class PaymentDomainServiceImpl implements PaymentDomainService {

    /// @param payment
    /// @param creditEntry
    /// @return
    @Override
    public CreditHistory validateAndInitiatePayment(Payment payment, CreditEntry creditEntry) {
        // 1. Payment logic
        payment.validatePayment();
        payment.initializePayment();

        // 2. CreditEntry logic → ដកលុយពី credit របស់ customer
        creditEntry.subtractCreditAmount(payment.getPrice());

        // 3. Payment success
        payment.updateStatus(PaymentStatus.COMPLETED);

        // 4. CreditHistory → កត់ត្រាថាបានដកលុយ (DEBIT)
        return CreditHistory.builder()
                .id(new CreditHistoryId(UUID.randomUUID()))
                .customerId(payment.getCustomerId())
                .amount(payment.getPrice())
                .transactionType(TransactionType.DEBIT)
                .build();
    }

    /// @param payment
    /// @param paymentStatus
    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.updateStatus(paymentStatus);
    }
}
