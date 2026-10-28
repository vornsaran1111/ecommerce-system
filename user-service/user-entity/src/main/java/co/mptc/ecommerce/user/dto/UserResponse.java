package co.mptc.ecommerce.user.dto;

public record UserResponse(
        String id,
        String familyName,
        String givenName,
        String gender,
        String birthday,
        String mobilePhone,
        String address
) {
}
