package co.mptc.ecommerce.customer.restapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


public record CustomerUpdateRequest(
        String familyName,
        String givenName,
        @NotBlank
        @Email
        String email,
        String phoneNumber
) {
}
