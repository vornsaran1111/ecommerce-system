package co.mptc.ecommerce.user.exception;

import co.mptc.ecommerce.customer.restapi.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class UsernameConflictException extends GlobalException {
    public UsernameConflictException(HttpStatus status,String message) {
        super(status,message);
    }
}
