package co.mptc.ecommerce.business.domain.event;

import co.mptc.ecommerce.business.domain.entity.OrderApproval;
import co.mptc.ecommerce.order.domain.event.DomainEvent;
import co.mptc.ecommerce.order.domain.valueObject.BusinessId;

import java.time.ZonedDateTime;
import java.util.List;

public abstract class OrderApprovalEvent implements DomainEvent<OrderApproval> {

    private final OrderApproval orderApproval;
    private final BusinessId businessId;
    private final List<String> failureMessages;
    private final ZonedDateTime createdAt;

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public BusinessId getBusinessId() {
        return businessId;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public OrderApprovalEvent(OrderApproval orderApproval, BusinessId businessId, List<String> failureMessages, ZonedDateTime createdAt) {
        this.orderApproval = orderApproval;
        this.businessId = businessId;
        this.failureMessages = failureMessages;
        this.createdAt = createdAt;
    }
}
