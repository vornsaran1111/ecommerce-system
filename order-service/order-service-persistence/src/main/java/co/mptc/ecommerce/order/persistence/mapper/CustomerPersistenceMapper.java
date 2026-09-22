package co.mptc.ecommerce.order.persistence.mapper;

import co.mptc.ecommerce.order.domain.entity.Customer;
import co.mptc.ecommerce.order.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerPersistenceMapper {

    @Mapping(source = "id", target = "id.value")
    Customer customerEntityToCustomer(CustomerEntity customerEntity);

}
