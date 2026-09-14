package co.mptc.ecommerce.domain.dto;

import co.mptc.ecommerce.domain.valueObject.BusinessId;
import co.mptc.ecommerce.domain.valueObject.CustomerId;
import co.mptc.ecommerce.domain.valueObject.Money;
import co.mptc.ecommerce.domain.valueObject.StreetAddress;

public record CreateOrderRequest(
        CustomerId customerId,
        BusinessId businessId,
        StreetAddress deliveryAddress,
        Money price
) {

}
