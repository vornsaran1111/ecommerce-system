package co.mptc.ecommerce.order.restapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;


@Builder
public record OrderAddressRequest(
        @NotNull
        @Size(min = 20)
        String street,
        @NotNull
        @Size(min = 10)
        String postalCode,
        @NotNull
        @Size(min = 30)
        String city) {
}
