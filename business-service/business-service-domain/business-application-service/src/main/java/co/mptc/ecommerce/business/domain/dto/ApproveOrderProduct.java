package co.mptc.ecommerce.business.domain.dto;

import java.util.UUID;

public record ApproveOrderProduct(
        UUID productId,
        int quantity
) {
}
