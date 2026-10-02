package co.mptc.ecommerce.business.persistence.mapper;

import co.mptc.ecommerce.business.domain.entity.OrderApproval;
import co.mptc.ecommerce.business.persistence.entity.OrderApprovalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface  OrderApprovalPersistenceMapper {
    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "businessId.value", target = "businessId")
    @Mapping(source = "orderId.value", target = "orderId")
    OrderApprovalEntity orderApprovalToOrderApprovalEntity(OrderApproval orderApproval);

    @Mapping(source = "id", target = "id.value")
    @Mapping(source = "businessId", target = "businessId.value")
    @Mapping(source = "orderId", target = "orderId.value")
    OrderApproval orderApprovalEntityToOrderApproval(OrderApprovalEntity orderApprovalEntity);
}
