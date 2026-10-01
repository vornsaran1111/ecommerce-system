package co.mptc.ecommerce.customer.restapi.mapper;

import co.mptc.ecommerce.customer.domain.dto.CreateCustomerCommand;
import co.mptc.ecommerce.customer.domain.dto.CustomerResult;
import co.mptc.ecommerce.customer.restapi.dto.CustomerCreateRequest;
import co.mptc.ecommerce.customer.restapi.dto.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerWebMapper {

//    @Mapping(source = "customerId", target = "customerId")
    CreateCustomerCommand customerCreateRequestToCreateCustomerCommand(CustomerCreateRequest request);

    CustomerResponse customerResultToCustomerResponse(CustomerResult result);
}
