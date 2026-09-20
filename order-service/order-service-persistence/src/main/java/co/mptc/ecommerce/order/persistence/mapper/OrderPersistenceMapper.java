package co.mptc.ecommerce.order.persistence.mapper;

import co.mptc.ecommerce.order.domain.entity.Business;
import co.mptc.ecommerce.order.domain.entity.Customer;
import co.mptc.ecommerce.order.persistence.entity.BusinessEntity;
import co.mptc.ecommerce.order.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderPersistenceMapper {

    @Mapping(source = "id", target = "id.value")
    Customer customerEntityToCustomer(CustomerEntity customerEntity);

    @Mapping(source = "businessId", target = "id.value")
    Business businessEntityToBusiness(BusinessEntity businessEntity);
}
