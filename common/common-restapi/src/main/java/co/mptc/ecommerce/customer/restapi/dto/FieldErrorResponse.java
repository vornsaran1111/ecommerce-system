package co.mptc.ecommerce.customer.restapi.dto;

public record FieldErrorResponse(
        String field,
        String code,
        String reason

) {
}
