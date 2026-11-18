package co.mptc.ecommerce.customer.restapi.exception;

import co.mptc.ecommerce.client.exception.UserServiceUnavailableException;
import co.mptc.ecommerce.customer.domain.exception.CustomerAlreadyExistsException;
import co.mptc.ecommerce.customer.domain.exception.CustomerDomainException;
import co.mptc.ecommerce.customer.domain.exception.CustomerNotFoundException;
import co.mptc.ecommerce.customer.restapi.dto.RestApiErrorResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerGlobalExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestApiErrorResponse<?> handleCustomerNotFound(CustomerNotFoundException e) {
        return error(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public RestApiErrorResponse<?> handleCustomerAlreadyExists(CustomerAlreadyExistsException e) {
        return error(HttpStatus.CONFLICT, e.getMessage());
    }

    // Base domain exception = a broken business rule, which is a client error.
    @ExceptionHandler(CustomerDomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiErrorResponse<?> handleCustomerDomainException(CustomerDomainException e) {
        return error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(UserServiceUnavailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public RestApiErrorResponse<?> handleUserServiceUnavailable(UserServiceUnavailableException e) {
        return error(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
    }

    // A failure coming straight back from user-service (e.g. GET /check-user/{id}).
    // 404 is passed through as 404; anything else becomes 502 instead of leaking a stack trace.
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<RestApiErrorResponse<?>> handleFeignException(FeignException e) {
        if (e.status() == HttpStatus.NOT_FOUND.value()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error(HttpStatus.NOT_FOUND, "User not found in user-service"));
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(error(HttpStatus.BAD_GATEWAY, "user-service returned status " + e.status()));
    }

    private RestApiErrorResponse<?> error(HttpStatus status, String message) {
        return RestApiErrorResponse.builder()
                .code(status.getReasonPhrase())
                .message(message)
                .build();
    }
}
