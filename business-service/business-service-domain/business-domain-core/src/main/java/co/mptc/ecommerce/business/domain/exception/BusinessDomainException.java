package co.mptc.ecommerce.business.domain.exception;

import co.mptc.ecommerce.order.domain.exception.DomainException;

public class BusinessDomainException extends DomainException {
    public BusinessDomainException(String message) {
        super(message);
    }

    public BusinessDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
