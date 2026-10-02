package co.mptc.ecommerce.business.domain.port.output;

import co.mptc.ecommerce.business.domain.entity.Business;

import java.util.Optional;

public interface BusinessRepository {
    // load active flag + real product information (name, price, available) from database
    Optional<Business> findBusinessInformation(Business business);
}
