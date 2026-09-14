package co.mptc.ecommerce.order.domain.event;

import co.mptc.ecommerce.order.domain.entity.Order;

import java.time.ZonedDateTime;

public class OderPainEvent extends OrderEvent{
    public OderPainEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
