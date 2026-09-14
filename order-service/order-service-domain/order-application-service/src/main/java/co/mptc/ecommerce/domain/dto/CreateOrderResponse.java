package co.mptc.ecommerce.domain.dto;

import co.mptc.ecommerce.domain.valueObject.OrderId;

public record CreateOrderResponse(

        OrderId orderId
) {
}
