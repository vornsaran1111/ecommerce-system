package co.mptc.ecommerce.order.domain.service;

import co.mptc.ecommerce.order.domain.entity.Business;
import co.mptc.ecommerce.order.domain.entity.Order;
import co.mptc.ecommerce.order.domain.entity.Product;
import co.mptc.ecommerce.order.domain.event.OrderCancelledEvent;
import co.mptc.ecommerce.order.domain.event.OrderCreatedEvent;
import co.mptc.ecommerce.order.domain.event.OrderPaidEvent;
import co.mptc.ecommerce.order.domain.exception.OrderDomainException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderDomainServiceImpl implements OrderDomainService {

    /// @param order
    /// @param business
    /// @return
    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Business business) {
        if (!business.isActive()) {
            throw new OrderDomainException("Business with ID: " + business.getId() + " is not active.");
        }

        //list OrderItems
        order.getOrderItems().forEach(orderItem -> {
            business.getProducts().forEach(businessProduct -> {
                Product currentProduct = orderItem.getProduct();
                if (businessProduct.equals(currentProduct)) {
                    currentProduct.updateConfirmedNameAndPrice(businessProduct.getName(),
                            businessProduct.getPrice());
                }
            });
        });

        //set product information
        order.validationOrder();
        order.initializeOrder();
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    /// @param order
    /// @return
    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    /// @param order
    @Override
    public void approveOrder(Order order) {
        order.approve();

    }

    /// @param order
    /// @param failureMessages
    /// @return
    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    /// @param order
    /// @param failureMessages
    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);

    }
}
