package co.mptc.ecommerce.customer.domain.port.output;

import co.mptc.ecommerce.core.PageResponse;
import co.mptc.ecommerce.customer.domain.dto.CustomerResult;
import co.mptc.ecommerce.customer.domain.entity.Customer;
import co.mptc.ecommerce.order.domain.valueObject.CustomerId;
import co.mptc.ecommerce.order.domain.valueObject.Email;

import java.util.Optional;

public interface CustomerRepository {

    PageResponse<CustomerResult> findAll(int page, int size);

    Customer save(Customer customer);

    Optional<Customer> findById(CustomerId customerId);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
