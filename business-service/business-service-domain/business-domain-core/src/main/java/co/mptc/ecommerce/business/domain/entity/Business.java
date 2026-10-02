package co.mptc.ecommerce.business.domain.entity;

import co.mptc.ecommerce.order.domain.entity.AggregateRoot;
import co.mptc.ecommerce.order.domain.valueObject.*;

import java.util.List;
import java.util.UUID;

public class Business extends AggregateRoot<BusinessId> {

    private OrderApproval orderApproval;
    private boolean active;
    private final OrderDetail orderDetail;

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public boolean isActive() {
        return active;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    private Business(Builder builder) {
       super.setId(builder.id);
        orderApproval = builder.orderApproval;
        active = builder.active;
        orderDetail = builder.orderDetail;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private BusinessId id;
        private OrderApproval orderApproval;
        private boolean active;
        private OrderDetail orderDetail;

        private Builder() {
        }


        public Builder id(BusinessId val) {
            id = val;
            return this;
        }

        public Builder orderApproval(OrderApproval val) {
            orderApproval = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Builder orderDetail(OrderDetail val) {
            orderDetail = val;
            return this;
        }

        public Business build() {
            return new Business(this);
        }
    }
    // ============== start critical business logic ===========//

    public void validateOrder(List<String> failureMessages) {
        validateBusinessActive(failureMessages);
        validateOrderStatus(failureMessages);
        validateProductAvailability(failureMessages);
        validateOrderTotal(failureMessages);
    }

    private void validateBusinessActive(List<String> failureMessages) {
        if (!active) {
            failureMessages.add("Business with id " + getId().value() + " is not active");
        }
    }
    private void validateOrderStatus(List<String> failureMessages) {
        if (orderDetail.getOrderStatus() != OrderStatus.PAID) {
            failureMessages.add("Order is not paid");
        }
    }

    private void validateProductAvailability(List<String> failureMessages) {
        orderDetail.getProducts().stream()
                .filter(product -> !product.isAvailable())
                .forEach(product -> failureMessages.add(
                        "Product with id " + product.getId().value() + " is not available"));
    }

    private void validateOrderTotal(List<String> failureMessages) {
        Money productsTotal = orderDetail.getProducts().stream()
                // price is null when the product does not belong to this business
                .map(product -> product.getPrice() == null
                        ? Money.ZERO
                        : product.getPrice().multiply(product.getQuantity()))
                .reduce(Money.ZERO, Money::add);

        if (!orderDetail.getTotalAmount().equals(productsTotal)) {
            failureMessages.add("Order total amount does not match product total");
        }
    }

    public void constructOrderApproval(OrderApprovalStatus status) {
        orderApproval = OrderApproval.builder()
                .id(new OrderApprovalId(UUID.randomUUID()))
                .businessId(getId())
                .orderId(orderDetail.getId())
                .approvalStatus(status)
                .build();
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    // ============== end critical business logic ===========//
}
