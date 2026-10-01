package co.mptc.ecommerce.customer.domain.dto;

import co.mptc.ecommerce.order.domain.valueObject.CustomerStatus;
import co.mptc.ecommerce.order.domain.valueObject.LoyaltyTier;

import java.util.UUID;

public record CustomerResult(
        UUID customerId,
        String username,
        String familyName,
        String givenName,
        String email,
        String phoneNumber,
        LoyaltyTier loyaltyTier,
        CustomerStatus status
) {
}
