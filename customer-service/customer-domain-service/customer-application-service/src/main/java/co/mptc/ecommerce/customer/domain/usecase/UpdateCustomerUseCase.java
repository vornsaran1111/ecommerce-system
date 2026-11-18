package co.mptc.ecommerce.customer.domain.usecase;

import co.mptc.ecommerce.customer.domain.dto.UpdateCustomerCommand;
import co.mptc.ecommerce.customer.domain.dto.UpdateCustomerResult;
import co.mptc.ecommerce.customer.domain.entity.Customer;
import co.mptc.ecommerce.customer.domain.event.CustomerUpdatedEvent;
import co.mptc.ecommerce.customer.domain.exception.CustomerAlreadyExistsException;
import co.mptc.ecommerce.customer.domain.exception.CustomerNotFoundException;
import co.mptc.ecommerce.customer.domain.port.output.CustomerRepository;
import co.mptc.ecommerce.customer.domain.service.CustomerDomainService;
import co.mptc.ecommerce.order.domain.valueObject.CustomerId;
import co.mptc.ecommerce.order.domain.valueObject.Email;
import co.mptc.ecommerce.order.domain.valueObject.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateCustomerUseCase {
    private final CustomerDomainService customerDomainService;
    private final CustomerRepository customerRepository;

    @Transactional
    public UpdateCustomerResult execute(UpdateCustomerCommand updateCustomerCommand) {
        log.info("Execute UpdateCustomerUseCase : {}", updateCustomerCommand);

        Customer customer = customerRepository.findById(new CustomerId(updateCustomerCommand.customerId()))
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found with id: " + updateCustomerCommand.customerId()));

        Email newEmail = new Email(updateCustomerCommand.email());
        PhoneNumber newPhoneNumber = updateCustomerCommand.phoneNumber() == null
                ? null
                : new PhoneNumber(updateCustomerCommand.phoneNumber());

        if (!newEmail.equals(customer.getEmail()) && customerRepository.existsByEmail(newEmail.value())) {
            throw new CustomerAlreadyExistsException("Email already exists: " + newEmail.value());
        }

        // The domain service applies the new values to the aggregate and returns the event.
        CustomerUpdatedEvent customerUpdatedEvent = customerDomainService.updateCustomer(
                customer,
                updateCustomerCommand.familyName(),
                updateCustomerCommand.givenName(),
                newEmail,
                newPhoneNumber);

        Customer savedCustomer = customerRepository.save(customer);

        log.info("Customer updated with id: {} at {}",
                savedCustomer.getId().value(), customerUpdatedEvent.getUpdatedAt());
        return new UpdateCustomerResult(savedCustomer.getId().value());
    }
}
