package co.mptc.ecommerce.payment.persistence.adapter;

import co.mptc.ecommerce.payment.domain.entity.Payment;
import co.mptc.ecommerce.payment.domain.port.output.PaymentRepository;
import co.mptc.ecommerce.payment.persistence.entity.PaymentEntity;
import co.mptc.ecommerce.payment.persistence.mapper.PaymentPersistenceMapper;
import co.mptc.ecommerce.payment.persistence.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentPersistenceMapper paymentPersistenceMapper;

    @Override
    public Payment savePayment(Payment payment) {
        PaymentEntity paymentEntity = paymentPersistenceMapper.paymentToPaymentEntity(payment);
        PaymentEntity savePaymentEntity = paymentJpaRepository.save(paymentEntity);
        return paymentPersistenceMapper.paymentEntityToPayment(savePaymentEntity);
    }
}