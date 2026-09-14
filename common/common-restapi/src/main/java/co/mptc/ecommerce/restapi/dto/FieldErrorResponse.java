package co.mptc.ecommerce.restapi.dto;

public record FieldErrorResponse(
        String field,
        String code,
        String reason

) {
}
