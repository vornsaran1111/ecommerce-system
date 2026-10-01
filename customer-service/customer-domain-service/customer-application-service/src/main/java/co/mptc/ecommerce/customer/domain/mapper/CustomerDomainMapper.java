package co.mptc.ecommerce.customer.domain.mapper;

import co.mptc.ecommerce.customer.domain.entity.Customer;
import co.mptc.ecommerce.customer.domain.dto.CreateCustomerCommand;
import co.mptc.ecommerce.customer.domain.dto.CustomerResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerDomainMapper {
    @Mapping(source = "email", target = "email.value")
    @Mapping(source = "phoneNumber", target = "phoneNumber.number")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "loyaltyTier", ignore = true)
    @Mapping(target = "status", ignore = true)
    Customer createCustomerCommandToCustomer(CreateCustomerCommand command);

    @Mapping(source = "id.value", target = "customerId")
    @Mapping(source = "email.value", target = "email")
    @Mapping(source = "phoneNumber.number", target = "phoneNumber")
    CustomerResult customerToCustomerResult(Customer customer);
}
