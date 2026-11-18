package co.mptc.ecommerce.customer.restapi.mapper;


import co.mptc.ecommerce.core.PageResponse;
import co.mptc.ecommerce.customer.domain.dto.*;
import co.mptc.ecommerce.customer.restapi.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

// unmappedTargetPolicy = ERROR: a response field that nothing maps onto would otherwise
// be silently serialised as null (that is how UpdateCustomerResult.customerID slipped through).
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CustomerWebMapper {

    // customerId is assigned by the domain (initiateCustomer), never sent by the client
    @Mapping(target = "customerId", ignore = true)
    CreateCustomerCommand customerCreateRequestToCreateCustomerCommand(CustomerCreateRequest customerCreateRequest);

    CustomerCreateResponse createCustomerResultToCustomerCreateResponse(CreateCustomerResult createCustomerResult);

    // customerId comes from the path, the rest from the request body
    @Mapping(source = "customerId", target = "customerId")
    UpdateCustomerCommand customerUpdateRequestToUpdateCustomerCommand(UUID customerId, CustomerUpdateRequest customerUpdateRequest);

    CustomerUpdateResponse updateCustomerResultToCustomerUpdateResponse(UpdateCustomerResult updateCustomerResult);

    PageResponse<CustomerResponse> toCustomerResponse(PageResponse<CustomerResult> result);
}
