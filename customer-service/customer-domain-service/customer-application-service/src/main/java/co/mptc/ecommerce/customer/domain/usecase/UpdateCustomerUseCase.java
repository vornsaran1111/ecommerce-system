package co.mptc.ecommerce.customer.domain.usecase;

import co.mptc.ecommerce.customer.domain.entity.Customer;
import co.mptc.ecommerce.customer.domain.exception.CustomerDomainException;
import co.mptc.ecommerce.customer.domain.service.CustomerDomainService;
import co.mptc.ecommerce.customer.domain.dto.CustomerResult;
import co.mptc.ecommerce.customer.domain.dto.UpdateCustomerCommand;
import co.mptc.ecommerce.customer.domain.exception.CustomerNotFoundException;
import co.mptc.ecommerce.customer.domain.mapper.CustomerDomainMapper;
import co.mptc.ecommerce.customer.domain.port.output.CustomerRepository;
import co.mptc.ecommerce.order.domain.valueObject.CustomerId;
import co.mptc.ecommerce.order.domain.valueObject.Email;
import co.mptc.ecommerce.order.domain.valueObject.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateCustomerUseCase {
    private final CustomerDomainService customerDomainService;
    private final CustomerDomainMapper customerDomainMapper;
    private final CustomerRepository customerRepository;

    public CustomerResult execute(UpdateCustomerCommand command) {

        log.info("executing UpdateCustomerUseCase: {}", command);

        //1. load customer
        Customer customer = customerRepository.findCustomer(new CustomerId(command.customerId()))
                .orElseThrow(() -> new CustomerNotFoundException("Could not find customer with id: " + command.customerId()));

        //2. new email must not belong to another customer
        Email newEmail = new Email(command.email());
        if (!newEmail.equals(customer.getEmail()) && customerRepository.existsByEmail(newEmail)) {
            throw new CustomerDomainException("Email already exists: " + command.email());
        }

        //3. domain logic (must be ACTIVE → change fields → validate)
        customerDomainService.updateCustomer(customer,
                command.familyName(),
                command.givenName(),
                newEmail,
                new PhoneNumber(command.phoneNumber()));

        //4. save
        Customer saveCustomer = customerRepository.saveCustomer(customer);

        return customerDomainMapper.customerToCustomerResult(saveCustomer);
    }
}
