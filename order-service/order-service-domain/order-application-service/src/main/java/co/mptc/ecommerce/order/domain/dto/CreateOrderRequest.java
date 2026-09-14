package co.mptc.ecommerce.order.domain.dto;

import co.mptc.ecommerce.order.domain.valueObject.BusinessId;
import co.mptc.ecommerce.order.domain.valueObject.CustomerId;
import co.mptc.ecommerce.order.domain.valueObject.Money;
import co.mptc.ecommerce.order.domain.valueObject.StreetAddress;

public record CreateOrderRequest(
        CustomerId customerId,
        BusinessId businessId,
        StreetAddress deliveryAddress,
        Money price
) {

}
