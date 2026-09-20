package co.mptc.ecommerce.order.persistence.adapter;

import co.mptc.ecommerce.order.domain.entity.Business;
import co.mptc.ecommerce.order.domain.port.output.BusinessRepository;
import co.mptc.ecommerce.order.persistence.mapper.OrderPersistenceMapper;
import co.mptc.ecommerce.order.persistence.repository.BusinessJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BusinessRepositoryAdapter implements BusinessRepository {

    private final BusinessJpaRepository businessJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;

    /// @param businessId
    /// @return
    @Override
    public Optional<Business> findBusiness(UUID businessId) {
       return businessJpaRepository.findById(businessId)
               .map(orderPersistenceMapper::businessEntityToBusiness);

    }
}
