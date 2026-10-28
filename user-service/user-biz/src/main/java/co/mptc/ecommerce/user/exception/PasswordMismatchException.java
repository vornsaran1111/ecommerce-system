package co.mptc.ecommerce.user.exception;

import co.mptc.ecommerce.customer.restapi.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends GlobalException {
    public PasswordMismatchException(HttpStatus status,String message) {
        super(status,message);
    }
}
