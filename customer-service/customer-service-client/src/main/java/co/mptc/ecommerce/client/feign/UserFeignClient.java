package co.mptc.ecommerce.client.feign;

import co.mptc.ecommerce.client.dto.UserResponse;
import co.mptc.ecommerce.client.fallback.UserFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        path = "/api/users",
        fallbackFactory = UserFeignClientFallbackFactory.class)
public interface UserFeignClient {

    @GetMapping("/{id}")
    UserResponse getById(@PathVariable String id);
}
