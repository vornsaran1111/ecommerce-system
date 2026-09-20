package co.mptc.ecommerce.order.domain.port.output;

import co.mptc.ecommerce.order.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomer(UUID customerId);
}
