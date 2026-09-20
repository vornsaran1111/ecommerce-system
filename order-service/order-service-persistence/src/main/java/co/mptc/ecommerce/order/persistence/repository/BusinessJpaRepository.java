package co.mptc.ecommerce.order.persistence.repository;

import co.mptc.ecommerce.order.persistence.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessJpaRepository extends JpaRepository<BusinessEntity, UUID> {

}
