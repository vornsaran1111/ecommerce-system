package co.mptc.ecommerce.business.domain.entity;

import co.mptc.ecommerce.order.domain.entity.BaseEntity;
import co.mptc.ecommerce.order.domain.valueObject.BusinessId;
import co.mptc.ecommerce.order.domain.valueObject.OrderApprovalId;
import co.mptc.ecommerce.order.domain.valueObject.OrderApprovalStatus;
import co.mptc.ecommerce.order.domain.valueObject.OrderId;

public class OrderApproval extends BaseEntity<OrderApprovalId> {
    private final BusinessId businessId;
    private final OrderId orderId;
    private final OrderApprovalStatus approvalStatus;

    public BusinessId getBusinessId() {
        return businessId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public OrderApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    private OrderApproval(Builder builder) {
        super.setId(builder.id);
        businessId = builder.businessId;
        orderId = builder.orderId;
        approvalStatus = builder.approvalStatus;
    }
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private OrderApprovalId id;
        private BusinessId businessId;
        private OrderId orderId;
        private OrderApprovalStatus approvalStatus;

        private Builder() {
        }



        public Builder id(OrderApprovalId val) {
            id = val;
            return this;
        }

        public Builder businessId(BusinessId val) {
            businessId = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder approvalStatus(OrderApprovalStatus val) {
            approvalStatus = val;
            return this;
        }

        public OrderApproval build() {
            return new OrderApproval(this);
        }
    }
}
