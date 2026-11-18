package co.mptc.ecommerce.customer.restapi.dto;

import java.util.UUID;

public record CustomerUpdateResponse(
        UUID customerId
) {
}
