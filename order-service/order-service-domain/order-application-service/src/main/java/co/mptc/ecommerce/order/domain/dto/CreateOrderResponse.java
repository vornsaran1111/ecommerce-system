package co.mptc.ecommerce.order.domain.dto;

import co.mptc.ecommerce.order.domain.valueObject.OrderId;

public record CreateOrderResponse(

        OrderId orderId
) {
}
