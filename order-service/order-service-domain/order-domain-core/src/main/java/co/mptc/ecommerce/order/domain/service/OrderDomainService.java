package co.mptc.ecommerce.order.domain.service;

import co.mptc.ecommerce.order.domain.entity.Business;
import co.mptc.ecommerce.order.domain.entity.Order;
import co.mptc.ecommerce.order.domain.event.OrderPaidEvent;
import co.mptc.ecommerce.order.domain.event.OrderCancelledEvent;
import co.mptc.ecommerce.order.domain.event.OrderCreatedEvent;

import java.util.List;

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Business business);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}
