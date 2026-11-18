package co.mptc.ecommerce.customer.domain.usecase;

import co.mptc.ecommerce.client.dto.UserResponse;
import co.mptc.ecommerce.client.feign.UserFeignClient;
import co.mptc.ecommerce.customer.domain.dto.CreateCustomerCommand;
import co.mptc.ecommerce.customer.domain.dto.CreateCustomerResult;
import co.mptc.ecommerce.customer.domain.entity.Customer;
import co.mptc.ecommerce.customer.domain.event.CustomerCreatedEvent;
import co.mptc.ecommerce.customer.domain.exception.CustomerAlreadyExistsException;
import co.mptc.ecommerce.customer.domain.exception.CustomerDomainException;
import co.mptc.ecommerce.customer.domain.exception.CustomerNotFoundException;
import co.mptc.ecommerce.customer.domain.mapper.CustomerDomainMapper;
import co.mptc.ecommerce.customer.domain.port.output.CustomerRepository;
import co.mptc.ecommerce.customer.domain.service.CustomerDomainService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateCustomerUseCase {
    private final CustomerDomainService customerDomainService; // business rules (from customer-domain-core)
    private final CustomerRepository customerRepository;
    private final CustomerDomainMapper customerDomainMapper;
    private final UserFeignClient userFeignClient;

    @Transactional
    public CreateCustomerResult execute(CreateCustomerCommand createCustomerCommand) {
        log.info("Execute CreateCustomerUseCase : {}", createCustomerCommand);

        // ACL - verify user with user-service.
        // UserServiceUnavailableException is deliberately not caught here: the REST layer
        // maps it to 503, which is a different problem from an unknown userId.
        try {
            UserResponse userResponse = userFeignClient.getById(createCustomerCommand.userId());
            log.info("User found: {}", userResponse);
        } catch (FeignException.NotFound e) {
            throw new CustomerNotFoundException("User not found with id: " + createCustomerCommand.userId());
        }

        if (customerRepository.existsByUsername(createCustomerCommand.username())) {
            throw new CustomerAlreadyExistsException("Username already exists: " + createCustomerCommand.username());
        }

        if (customerRepository.existsByEmail(createCustomerCommand.email())) {
            throw new CustomerAlreadyExistsException("Email already exists: " + createCustomerCommand.email());
        }

        Customer customer = customerDomainMapper.createCustomerCommandToCustomer(createCustomerCommand);
        CustomerCreatedEvent customerCreatedEvent = customerDomainService.validateAndInitiateCustomer(customer);
        Customer savedCustomer = customerRepository.save(customer);

        if (savedCustomer == null) {
            log.error("Could not save customer with username: {}", createCustomerCommand.username());
            throw new CustomerDomainException("Could not save customer " + createCustomerCommand.username());
        }

        log.info("Customer created with id: {} at {}",
                savedCustomer.getId().value(), customerCreatedEvent.getCreatedAt());
        return new CreateCustomerResult(savedCustomer.getId().value());
    }
}
