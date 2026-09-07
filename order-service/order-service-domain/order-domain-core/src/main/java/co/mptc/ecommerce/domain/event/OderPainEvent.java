package co.mptc.ecommerce.domain.event;

import co.mptc.ecommerce.domain.entity.Order;

import java.time.ZonedDateTime;

public class OderPainEvent extends OrderEvent{
    public OderPainEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
