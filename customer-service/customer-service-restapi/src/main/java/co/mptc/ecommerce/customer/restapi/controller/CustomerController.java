package co.mptc.ecommerce.customer.restapi.controller;

import co.mptc.ecommerce.customer.domain.dto.CreateCustomerCommand;
import co.mptc.ecommerce.customer.domain.dto.CustomerResult;
import co.mptc.ecommerce.customer.domain.dto.UpdateCustomerCommand;
import co.mptc.ecommerce.customer.domain.usecase.CreateCustomerUseCase;
import co.mptc.ecommerce.customer.domain.usecase.DeactivateCustomerUseCase;
import co.mptc.ecommerce.customer.domain.usecase.GetCustomerUseCase;
import co.mptc.ecommerce.customer.domain.usecase.UpdateCustomerUseCase;
import co.mptc.ecommerce.customer.restapi.dto.CustomerCreateRequest;
import co.mptc.ecommerce.customer.restapi.dto.CustomerResponse;
import co.mptc.ecommerce.customer.restapi.dto.CustomerUpdateRequest;
import co.mptc.ecommerce.customer.restapi.mapper.CustomerWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeactivateCustomerUseCase deactivateCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final CustomerWebMapper customerWebMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CustomerResponse createCustomer(@Valid @RequestBody CustomerCreateRequest request) {
        CreateCustomerCommand command = customerWebMapper.customerCreateRequestToCreateCustomerCommand(request);
        CustomerResult result = createCustomerUseCase.execute(command);
        return customerWebMapper.customerResultToCustomerResponse(result);
    }

    @GetMapping("/{customerId}")
    public CustomerResponse getCustomer(@PathVariable UUID customerId) {
        CustomerResult result = getCustomerUseCase.execute(customerId);
        return customerWebMapper.customerResultToCustomerResponse(result);
    }

    @PutMapping("/{customerId}")
    public CustomerResponse updateCustomer(@PathVariable UUID customerId,
                                           @Valid @RequestBody CustomerUpdateRequest request) {
        UpdateCustomerCommand command = new UpdateCustomerCommand(
                customerId,
                request.familyName(),
                request.givenName(),
                request.email(),
                request.phoneNumber());
        CustomerResult result = updateCustomerUseCase.execute(command);
        return customerWebMapper.customerResultToCustomerResponse(result);
    }

    @PatchMapping("/{customerId}/deactivate")
    public CustomerResponse deactivateCustomer(@PathVariable UUID customerId) {
        CustomerResult result = deactivateCustomerUseCase.execute(customerId);
        return customerWebMapper.customerResultToCustomerResponse(result);
    }
}
