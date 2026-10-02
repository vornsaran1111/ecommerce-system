package co.mptc.ecommerce.business.domain.entity;

import co.mptc.ecommerce.order.domain.entity.BaseEntity;
import co.mptc.ecommerce.order.domain.valueObject.Money;
import co.mptc.ecommerce.order.domain.valueObject.OrderId;
import co.mptc.ecommerce.order.domain.valueObject.OrderStatus;

import java.util.List;

public class OrderDetail extends BaseEntity<OrderId> {
    private final OrderStatus orderStatus;
    private final Money totalAmount;
    private final List<Product> products;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public List<Product> getProducts() {
        return products;
    }

    private OrderDetail(Builder builder) {
        super.setId(builder.id);
        orderStatus = builder.orderStatus;
        totalAmount = builder.totalAmount;
        products = builder.products;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private OrderId id;
        private OrderStatus orderStatus;
        private Money totalAmount;
        private List<Product> products;

        private Builder() {
        }



        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder totalAmount(Money val) {
            totalAmount = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public OrderDetail build() {
            return new OrderDetail(this);
        }
    }
}
