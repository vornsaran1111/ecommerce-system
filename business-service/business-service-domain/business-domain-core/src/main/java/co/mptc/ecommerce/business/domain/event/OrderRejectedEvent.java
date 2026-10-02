package co.mptc.ecommerce.business.domain.event;

import co.mptc.ecommerce.business.domain.entity.OrderApproval;
import co.mptc.ecommerce.order.domain.valueObject.BusinessId;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderRejectedEvent extends OrderApprovalEvent{
    public OrderRejectedEvent(OrderApproval orderApproval, BusinessId businessId, List<String> failureMessages, ZonedDateTime createdAt) {
        super(orderApproval, businessId, failureMessages, createdAt);
    }
}
