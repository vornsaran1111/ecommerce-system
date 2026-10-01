package co.mptc.ecommerce.customer.domain.event;

import co.mptc.ecommerce.customer.domain.entity.Customer;
import co.mptc.ecommerce.order.domain.event.DomainEvent;

public class CustomerEvent implements DomainEvent<Customer> {
    private final Customer customer;

    public CustomerEvent(Customer customer){
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
