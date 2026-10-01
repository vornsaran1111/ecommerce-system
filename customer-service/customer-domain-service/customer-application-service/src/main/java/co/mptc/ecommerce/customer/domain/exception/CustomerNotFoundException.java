package co.mptc.ecommerce.customer.domain.exception;

public class CustomerNotFoundException extends CustomerDomainException {
    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
