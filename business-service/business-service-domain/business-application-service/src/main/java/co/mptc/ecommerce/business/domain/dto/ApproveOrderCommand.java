package co.mptc.ecommerce.business.domain.dto;

import co.mptc.ecommerce.order.domain.valueObject.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ApproveOrderCommand(
        UUID businessId,
        UUID orderId,
        OrderStatus orderStatus,
        BigDecimal totalAmount,
        List<ApproveOrderProduct> products
) {
}
