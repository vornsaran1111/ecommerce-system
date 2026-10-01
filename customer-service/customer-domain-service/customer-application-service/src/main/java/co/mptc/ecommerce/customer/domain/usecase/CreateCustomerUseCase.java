package co.mptc.ecommerce.customer.domain.usecase;

import co.mptc.ecommerce.customer.domain.entity.Customer;
import co.mptc.ecommerce.customer.domain.event.CustomerCreatedEvent;
import co.mptc.ecommerce.customer.domain.exception.CustomerDomainException;
import co.mptc.ecommerce.customer.domain.service.CustomerDomainService;
import co.mptc.ecommerce.customer.domain.dto.CreateCustomerCommand;
import co.mptc.ecommerce.customer.domain.dto.CustomerResult;
import co.mptc.ecommerce.customer.domain.mapper.CustomerDomainMapper;
import co.mptc.ecommerce.customer.domain.port.output.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateCustomerUseCase {
    private final CustomerDomainService customerDomainService;
    private final CustomerDomainMapper customerDomainMapper;
    private final CustomerRepository customerRepository;

    public CustomerResult execute(CreateCustomerCommand command) {

        log.info("executing CreateCustomerUseCase: {}", command);

        //1. convert input object by map-struct
        Customer customer = customerDomainMapper.createCustomerCommandToCustomer(command);

        //2. domain logic (validate → generate id → BRONZE → ACTIVE)
        CustomerCreatedEvent createdEvent = customerDomainService.validateAndInitiateCustomer(customer);

        //3. username and email must be unique
        if (customerRepository.existsByUsername(customer.getUsername())) {
            throw new CustomerDomainException("Username already exists: " + customer.getUsername());
        }
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CustomerDomainException("Email already exists: " + customer.getEmail().value());
        }

        //4. save
        Customer saveCustomer = customerRepository.saveCustomer(customer);
        if (saveCustomer == null) {
            throw new CustomerDomainException("Could not save customer into database");
        }

        log.info("Customer created with id: {} at {}", saveCustomer.getId().value(), createdEvent.getCreatedAt());

        return customerDomainMapper.customerToCustomerResult(saveCustomer);
    }
}
