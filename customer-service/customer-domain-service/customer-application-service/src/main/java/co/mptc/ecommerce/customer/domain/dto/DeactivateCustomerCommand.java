package co.mptc.ecommerce.customer.domain.dto;

import java.util.UUID;

public record DeactivateCustomerCommand(
        UUID customerID
) {
}
