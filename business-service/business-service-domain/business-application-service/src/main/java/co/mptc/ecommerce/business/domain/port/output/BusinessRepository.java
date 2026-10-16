package co.mptc.ecommerce.business.domain.port.output;

import co.mptc.ecommerce.business.domain.entity.Business;

import java.util.Optional;

public interface BusinessRepository {
    Optional<Business> findBusinessInformation(Business business);
}
