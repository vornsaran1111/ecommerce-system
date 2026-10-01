package co.mptc.ecommerce.customer.domain.event;

import co.mptc.ecommerce.customer.domain.entity.Customer;
import co.mptc.ecommerce.order.domain.event.DomainEvent;
import co.mptc.ecommerce.order.domain.valueObject.CustomerId;

import java.time.ZonedDateTime;

public class CustomerDeactivatedEvent implements DomainEvent<Customer> {

    private final CustomerId customerId;
    private final ZonedDateTime deactivatedAt;

    public CustomerDeactivatedEvent(CustomerId customerId, ZonedDateTime deactivatedAt) {
        this.customerId = customerId;
        this.deactivatedAt = deactivatedAt;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public ZonedDateTime getDeactivatedAt() {
        return deactivatedAt;
    }
}

