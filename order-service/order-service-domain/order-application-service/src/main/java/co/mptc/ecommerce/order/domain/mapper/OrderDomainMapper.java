package co.mptc.ecommerce.order.domain.mapper;

import co.mptc.ecommerce.order.domain.dto.CommandOrderItem;
import co.mptc.ecommerce.order.domain.dto.CreateOrderCommand;
import co.mptc.ecommerce.order.domain.entity.Order;
import co.mptc.ecommerce.order.domain.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDomainMapper {

    @Mapping(source = "customerId", target = "customerId.value")
    @Mapping(source = "businessId", target = "businessId.value")
    @Mapping(source = "price", target = "price.amount")
    @Mapping(source = "items", target = "orderItems")
    Order createOrderCommandToOrder(CreateOrderCommand command);

    @Mapping(source = "productId", target = "product.id.value")
    @Mapping(source = "price", target = "price.amount")
    @Mapping(source = "subTotal", target = "subTotal.amount")
    OrderItem commandOrderItemToOrderItem(CommandOrderItem commandOrderItem);
}
