package co.mptc.ecommerce.business.persistence.repository;

import co.mptc.ecommerce.business.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

    // products of this business, only the ones in the order
    List<ProductEntity> findByBusinessIdAndIdIn(UUID businessId, List<UUID> ids);
}
