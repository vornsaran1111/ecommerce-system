package co.mptc.ecommerce.client.fallback;

import co.mptc.ecommerce.client.exception.UserServiceUnavailableException;
import co.mptc.ecommerce.client.feign.UserFeignClient;
import feign.FeignException;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Sentinel routes EVERY throwable of a feign call into the fallback, including a real
 * HTTP response such as 404. A plain `fallback` would therefore turn "user not found"
 * into "user service is unavailable". This factory keeps the original cause so the
 * caller can tell a real answer from user-service apart from never reaching it at all.
 */
@Component
@Slf4j
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable cause) {
        log.warn("user-service call failed: {}", cause.toString());
        return id -> {
            if (reachedUserService(cause)) {
                // A real HTTP response (404, 400, ...) - let the caller react to the status
                throw (FeignException) cause;
            }
            // Never reached user-service: no instance in Nacos, connection refused,
            // read timeout, or Sentinel blocked/degraded the call.
            throw new UserServiceUnavailableException("User service is unavailable now, please try again!");
        };
    }

    private boolean reachedUserService(Throwable cause) {
        // RetryableException (connect/read timeout, connection refused) never got an answer.
        if (!(cause instanceof FeignException feignException) || cause instanceof RetryableException) {
            return false;
        }
        // The load balancer synthesises a 503 when Nacos has no instance of user-service,
        // so a 503 here is also "never reached", not an answer from user-service.
        int status = feignException.status();
        return status > 0 && status != HttpStatus.SERVICE_UNAVAILABLE.value();
    }
}
