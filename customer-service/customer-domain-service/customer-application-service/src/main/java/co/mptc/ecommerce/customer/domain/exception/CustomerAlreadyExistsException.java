package co.mptc.ecommerce.customer.domain.exception;

public class CustomerAlreadyExistsException extends CustomerDomainException {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }

    public CustomerAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
