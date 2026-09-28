package co.mptc.ecommerce.payment.persistence.adapter;

import co.mptc.ecommerce.payment.domain.entity.CreditHistory;
import co.mptc.ecommerce.payment.domain.port.output.CreditHistoryRepository;
import co.mptc.ecommerce.payment.persistence.entity.CreditHistoryEntity;
import co.mptc.ecommerce.payment.persistence.mapper.CreditHistoryPersistenceMapper;
import co.mptc.ecommerce.payment.persistence.mapper.PaymentPersistenceMapper;
import co.mptc.ecommerce.payment.persistence.repository.CreditHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreditHistoryRepositoryAdapter implements CreditHistoryRepository {

    private final CreditHistoryJpaRepository creditHistoryJpaRepository;
    private final CreditHistoryPersistenceMapper  creditHistoryPersistenceMapper;

    @Override
    public CreditHistory save(CreditHistory creditHistory) {
        CreditHistoryEntity entity = creditHistoryPersistenceMapper.creditHistoryToCreditHistoryEntity(creditHistory);
        return creditHistoryPersistenceMapper.creditHistoryEntityToCreditHistory(creditHistoryJpaRepository.save(entity));
    }
}