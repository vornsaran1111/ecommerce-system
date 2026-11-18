package co.mptc.ecommerce.customer.restapi.controller;

import co.mptc.ecommerce.client.dto.UserResponse;
import co.mptc.ecommerce.client.feign.UserFeignClient;
import co.mptc.ecommerce.core.PageResponse;
import co.mptc.ecommerce.customer.domain.dto.CustomerResult;
import co.mptc.ecommerce.customer.domain.dto.GetCustomersQuery;
import co.mptc.ecommerce.customer.domain.usecase.GetAllCustomersUseCase;
import co.mptc.ecommerce.customer.domain.usecase.GetCustomerUseCase;
import co.mptc.ecommerce.customer.restapi.dto.CustomerResponse;
import co.mptc.ecommerce.customer.restapi.mapper.CustomerWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerQueryController {

    private final UserFeignClient userFeignClient;
    private final GetAllCustomersUseCase getAllCustomersUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final CustomerWebMapper customerWebMapper;

    @GetMapping
    public PageResponse<CustomerResponse> getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ) {
        PageResponse<CustomerResult> result = getAllCustomersUseCase.execute(new GetCustomersQuery(page, size));
        return customerWebMapper.toCustomerResponse(result);
    }

    @GetMapping("/{customerId}")
    public CustomerResponse getById(@PathVariable UUID customerId) {
        CustomerResult result = getCustomerUseCase.execute(customerId);
        return customerWebMapper.toCustomerResponse(result);
    }


    @GetMapping("/check-user/{userId}")
    public UserResponse checkUser(@PathVariable String userId) {
        return userFeignClient.getById(userId);
    }

}
