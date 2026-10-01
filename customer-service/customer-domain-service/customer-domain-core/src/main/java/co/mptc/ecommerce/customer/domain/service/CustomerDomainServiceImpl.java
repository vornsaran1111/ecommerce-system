package co.mptc.ecommerce.customer.domain.service;

import co.mptc.ecommerce.customer.domain.entity.Customer;
import co.mptc.ecommerce.customer.domain.event.CustomerCreatedEvent;
import co.mptc.ecommerce.customer.domain.event.CustomerDeactivatedEvent;
import co.mptc.ecommerce.customer.domain.event.CustomerUpdatedEvent;
import co.mptc.ecommerce.order.domain.valueObject.Email;
import co.mptc.ecommerce.order.domain.valueObject.PhoneNumber;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CustomerDomainServiceImpl implements CustomerDomainService{
    /// @param customer
    /// @return
    @Override
    public CustomerCreatedEvent validateAndInitiateCustomer(Customer customer) {
        customer.validateCustomer();
        customer.initiateCustomer();
        return new CustomerCreatedEvent(customer, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    /// @param customer
    /// @param familyName
    /// @param givenName
    /// @param email
    /// @param phoneNumber
    /// @return
    @Override
    public CustomerUpdatedEvent updateCustomer(Customer customer, String familyName, String givenName, Email email, PhoneNumber phoneNumber) {
        customer.updateCustomer(familyName, givenName, email, phoneNumber);
        return new CustomerUpdatedEvent(customer, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    /// @param customer
    /// @return
    @Override
    public CustomerDeactivatedEvent deactivateCustomer(Customer customer) {
        customer.deactivateCustomer();
        return new CustomerDeactivatedEvent(customer.getId(), ZonedDateTime.now(ZoneId.of("UTC")));
    }
}
