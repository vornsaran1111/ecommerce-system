package co.mptc.ecommerce.customer.restapi.dto;

import co.mptc.ecommerce.order.domain.valueObject.CustomerStatus;
import co.mptc.ecommerce.order.domain.valueObject.LoyaltyTier;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CustomerCreateResponse(
        UUID customerId
) {
}
