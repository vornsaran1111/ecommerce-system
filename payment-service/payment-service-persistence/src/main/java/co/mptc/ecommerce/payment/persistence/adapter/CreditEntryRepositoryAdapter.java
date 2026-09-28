package co.mptc.ecommerce.payment.persistence.adapter;

import co.mptc.ecommerce.order.domain.valueObject.CustomerId;
import co.mptc.ecommerce.payment.domain.entity.CreditEntry;
import co.mptc.ecommerce.payment.domain.port.output.CreditEntityRepository;
import co.mptc.ecommerce.payment.persistence.entity.CreditEntryEntity;
import co.mptc.ecommerce.payment.persistence.mapper.PaymentPersistenceMapper;
import co.mptc.ecommerce.payment.persistence.repository.CreditEntryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreditEntryRepositoryAdapter implements CreditEntityRepository {

    private final CreditEntryJpaRepository creditEntryJpaRepository;
    private final PaymentPersistenceMapper paymentPersistenceMapper;

    @Override
    public CreditEntry findByCustomerId(CustomerId customerId) {
        return creditEntryJpaRepository.findByCustomerId(customerId.value())
                .map(paymentPersistenceMapper::creditEntryEntityToCreditEntry)
                .orElse(null);   // use case checks null → throws PaymentDomainException
    }

    @Override
    public CreditEntry save(CreditEntry creditEntry) {
        CreditEntryEntity entity = paymentPersistenceMapper.creditEntryToCreditEntryEntity(creditEntry);
        // same id already in DB → JPA does UPDATE (not INSERT)
        return paymentPersistenceMapper.creditEntryEntityToCreditEntry(creditEntryJpaRepository.save(entity));
    }
}