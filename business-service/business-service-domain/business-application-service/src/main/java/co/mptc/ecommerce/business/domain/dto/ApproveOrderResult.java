package co.mptc.ecommerce.business.domain.dto;

import co.mptc.ecommerce.order.domain.valueObject.OrderApprovalStatus;

import java.util.List;
import java.util.UUID;

public record ApproveOrderResult(
        UUID orderApprovalId,
        UUID businessId,
        UUID orderId,
        OrderApprovalStatus approvalStatus,
        List<String> failureMessages
) {
}
