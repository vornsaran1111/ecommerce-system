package co.mptc.ecommerce.customer.domain.service;

import co.mptc.ecommerce.customer.domain.entity.Customer;
import co.mptc.ecommerce.customer.domain.event.CustomerCreatedEvent;
import co.mptc.ecommerce.customer.domain.event.CustomerDeactivatedEvent;
import co.mptc.ecommerce.customer.domain.event.CustomerUpdatedEvent;
import co.mptc.ecommerce.order.domain.valueObject.Email;
import co.mptc.ecommerce.order.domain.valueObject.PhoneNumber;

public interface CustomerDomainService {

    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);

    CustomerUpdatedEvent updateCustomer(Customer customer, String familyName, String givenName,
                                        Email email, PhoneNumber phoneNumber);

    CustomerDeactivatedEvent deactivateCustomer(Customer customer);
}
