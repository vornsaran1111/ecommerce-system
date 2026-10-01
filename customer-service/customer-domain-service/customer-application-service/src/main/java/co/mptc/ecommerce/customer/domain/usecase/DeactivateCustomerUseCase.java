package co.mptc.ecommerce.customer.domain.usecase;

import co.mptc.ecommerce.customer.domain.entity.Customer;
import co.mptc.ecommerce.customer.domain.event.CustomerDeactivatedEvent;
import co.mptc.ecommerce.customer.domain.service.CustomerDomainService;
import co.mptc.ecommerce.customer.domain.dto.CustomerResult;
import co.mptc.ecommerce.customer.domain.exception.CustomerNotFoundException;
import co.mptc.ecommerce.customer.domain.mapper.CustomerDomainMapper;
import co.mptc.ecommerce.customer.domain.port.output.CustomerRepository;
import co.mptc.ecommerce.order.domain.valueObject.CustomerId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeactivateCustomerUseCase {
    private final CustomerDomainService customerDomainService;
    private final CustomerDomainMapper customerDomainMapper;
    private final CustomerRepository customerRepository;

    public CustomerResult execute(UUID customerId) {

        log.info("executing DeactivateCustomerUseCase for customer id: {}", customerId);

        Customer customer = customerRepository.findCustomer(new CustomerId(customerId))
                .orElseThrow(() -> new CustomerNotFoundException("Could not find customer with id: " + customerId));

        // domain logic (must be ACTIVE → INACTIVE)
        CustomerDeactivatedEvent deactivatedEvent = customerDomainService.deactivateCustomer(customer);

        Customer saveCustomer = customerRepository.saveCustomer(customer);

        log.info("Customer {} deactivated at {}", deactivatedEvent.getCustomerId().value(), deactivatedEvent.getDeactivatedAt());

        return customerDomainMapper.customerToCustomerResult(saveCustomer);
    }
}
