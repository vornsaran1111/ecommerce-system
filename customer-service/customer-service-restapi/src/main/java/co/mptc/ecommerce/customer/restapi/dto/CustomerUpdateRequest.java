package co.mptc.ecommerce.customer.restapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CustomerUpdateRequest(
        @NotBlank
        String familyName,
        @NotBlank
        String givenName,
        @NotBlank
        String email,
        @NotBlank
        String phoneNumber
) {
}
