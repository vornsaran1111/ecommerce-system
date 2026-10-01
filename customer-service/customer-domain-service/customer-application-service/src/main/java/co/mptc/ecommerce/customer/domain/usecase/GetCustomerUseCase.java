package co.mptc.ecommerce.customer.domain.usecase;

import co.mptc.ecommerce.customer.domain.dto.CustomerResult;
import co.mptc.ecommerce.customer.domain.exception.CustomerNotFoundException;
import co.mptc.ecommerce.customer.domain.mapper.CustomerDomainMapper;
import co.mptc.ecommerce.customer.domain.port.output.CustomerRepository;
import co.mptc.ecommerce.order.domain.valueObject.CustomerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetCustomerUseCase {
    private final CustomerDomainMapper customerDomainMapper;
    private final CustomerRepository customerRepository;

    public CustomerResult execute(UUID customerId) {
        return customerRepository.findCustomer(new CustomerId(customerId))
                .map(customerDomainMapper::customerToCustomerResult)
                .orElseThrow(() -> new CustomerNotFoundException("Could not find customer with id: " + customerId));
    }
}
