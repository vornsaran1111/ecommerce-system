package co.mptc.ecommerce.payment.persistence.repository;

import co.mptc.ecommerce.payment.persistence.entity.CreditEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CreditEntryJpaRepository extends JpaRepository<CreditEntryEntity, UUID> {

    // Spring generates SQL from the method name: WHERE customer_id = ?
    Optional<CreditEntryEntity> findByCustomerId(UUID customerId);
}