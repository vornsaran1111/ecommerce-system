package co.mptc.ecommerce.order.domain.port.output;

import co.mptc.ecommerce.order.domain.entity.Business;

import java.util.Optional;
import java.util.UUID;

public interface BusinessRepository {
    Optional<Business> findBusiness(Business business);
}
