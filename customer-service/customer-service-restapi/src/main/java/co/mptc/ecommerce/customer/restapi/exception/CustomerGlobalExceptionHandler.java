package co.mptc.ecommerce.customer.restapi.exception;

import co.mptc.ecommerce.customer.domain.exception.CustomerDomainException;
import co.mptc.ecommerce.customer.domain.exception.CustomerNotFoundException;
import co.mptc.ecommerce.customer.restapi.dto.RestApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerGlobalExceptionHandler extends GlobalExceptionHandler{
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerDomainException.class)
    public RestApiErrorResponse<?> handleCustomerDomainException(CustomerDomainException e) {
        return RestApiErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public RestApiErrorResponse<?> handleCustomerNotFoundException(CustomerNotFoundException e) {
        return RestApiErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }

}
