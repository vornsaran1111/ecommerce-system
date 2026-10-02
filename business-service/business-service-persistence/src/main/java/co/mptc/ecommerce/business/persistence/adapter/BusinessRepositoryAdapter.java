package co.mptc.ecommerce.business.persistence.adapter;

import co.mptc.ecommerce.business.domain.entity.Business;
import co.mptc.ecommerce.business.domain.event.OrderApprovalEvent;
import co.mptc.ecommerce.business.domain.port.output.BusinessRepository;
import co.mptc.ecommerce.business.domain.service.BusinessDomainService;
import co.mptc.ecommerce.business.persistence.entity.ProductEntity;
import co.mptc.ecommerce.business.persistence.mapper.BusinessPersistenceMapper;
import co.mptc.ecommerce.business.persistence.repository.BusinessJpaRepository;
import co.mptc.ecommerce.business.persistence.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BusinessRepositoryAdapter implements BusinessRepository {
    private final BusinessJpaRepository businessJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final BusinessPersistenceMapper businessPersistenceMapper;


    /// @param business
    /// @return
    @Override
    public Optional<Business> findBusinessInformation(Business business) {
        List<UUID> productIds = businessPersistenceMapper.businessToProductIds(business);

        return businessJpaRepository.findById(business.getId().value())
                .map(businessEntity -> {
                    List<ProductEntity> productEntities =
                            productJpaRepository.findByBusinessIdAndIdIn(businessEntity.getId(), productIds);
                    return businessPersistenceMapper.businessEntityToBusiness(businessEntity, productEntities);
                });
    }
}
