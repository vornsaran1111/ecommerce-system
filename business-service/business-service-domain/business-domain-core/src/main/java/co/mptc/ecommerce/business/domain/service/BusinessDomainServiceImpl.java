package co.mptc.ecommerce.business.domain.service;

import co.mptc.ecommerce.business.domain.entity.Business;
import co.mptc.ecommerce.business.domain.event.OrderApprovalEvent;
import co.mptc.ecommerce.business.domain.event.OrderApprovedEvent;
import co.mptc.ecommerce.business.domain.event.OrderRejectedEvent;
import co.mptc.ecommerce.order.domain.valueObject.OrderApprovalStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class BusinessDomainServiceImpl implements BusinessDomainService{
    /// @param business
    /// @param failureMessages
    /// @return
    @Override
    public OrderApprovalEvent validateOrder(Business business, List<String> failureMessages) {
        business.validateOrder(failureMessages);

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));

        if (failureMessages.isEmpty()) {
            business.constructOrderApproval(OrderApprovalStatus.APPROVED);
            return new OrderApprovedEvent(business.getOrderApproval(), business.getId(), failureMessages, now);
        }

        business.constructOrderApproval(OrderApprovalStatus.REJECTED);
        return new OrderRejectedEvent(business.getOrderApproval(), business.getId(), failureMessages, now);
    }
}
