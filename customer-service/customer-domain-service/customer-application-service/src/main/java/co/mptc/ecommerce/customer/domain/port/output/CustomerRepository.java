package co.mptc.ecommerce.customer.domain.port.output;

import co.mptc.ecommerce.customer.domain.entity.Customer;
import co.mptc.ecommerce.order.domain.valueObject.CustomerId;
import co.mptc.ecommerce.order.domain.valueObject.Email;

import java.util.Optional;

public interface CustomerRepository {

    Customer saveCustomer(Customer customer);

    Optional<Customer> findCustomer(CustomerId customerId);

    boolean existsByUsername(String username);

    boolean existsByEmail(Email email);
}
