package co.mptc.ecommerce.domain.valueObject;

import java.util.UUID;

public record StreetAddress(UUID id,
                            String street,
                            String postalCode,
                            String city) {
}
