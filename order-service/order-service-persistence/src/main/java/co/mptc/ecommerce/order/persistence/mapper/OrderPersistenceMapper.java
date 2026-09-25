package co.mptc.ecommerce.order.persistence.mapper;

import co.mptc.ecommerce.order.domain.entity.Order;
import co.mptc.ecommerce.order.domain.entity.OrderItem;
import co.mptc.ecommerce.order.domain.valueObject.StreetAddress;
import co.mptc.ecommerce.order.persistence.entity.OrderAddressEntity;
import co.mptc.ecommerce.order.persistence.entity.OrderEntity;
import co.mptc.ecommerce.order.persistence.entity.OrderItemEntity;
import org.mapstruct.*;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderPersistenceMapper {

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "customerId.value", target = "customerId")
    @Mapping(source = "businessId.value", target = "businessId")
    @Mapping(source = "price.amount", target = "price")
    @Mapping(source = "trackingId.value", target = "trackingId")
    @Mapping(source = "failureMessages", target = "failureMessages", qualifiedByName = "mapFailureMessages")
    @Mapping(source = "orderItems", target = "items")
    @Mapping(source = "deliveryAddress",target = "orderAddress")
    OrderEntity orderToOrderEntity(Order order);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    OrderAddressEntity deliveryAddressToOrderAddressEntity(StreetAddress streetAddress);


    @Named("mapFailureMessages")
    default String mapFailureMessages(List<String> failureMessages) {
        return failureMessages == null ? null : String.join(",", failureMessages);
    }

    // Issue Map List<OrderItem> to List<OrderItemEntity>
    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "product.id.value", target = "productId")
    @Mapping(source = "price.amount", target = "price")
    @Mapping(source = "subTotal.amount", target = "subTotal")
    OrderItemEntity orderItemToOrderItemEntity(OrderItem orderItem);

    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "customerId.value", source = "customerId")
    @Mapping(target = "businessId.value", source = "businessId")
    @Mapping(target = "price.amount", source = "price")
    @Mapping(target = "trackingId.value", source = "trackingId")
    @Mapping(target = "failureMessages", source = "failureMessages", qualifiedByName = "mapFailureMessagesToList")
    @Mapping(target = "deliveryAddress", source = "orderAddress")
    Order orderEntityToOrder(OrderEntity orderEntity);

    //Issue Map List<OrderItemEntity> to List<OrderItem>

    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "product.id.value", source = "productId")
    @Mapping(target = "price.amount", source = "price")
    @Mapping(target = "subTotal.amount", source = "subTotal")
    OrderItem orderItemEntityToOrderItem(OrderItemEntity orderItemEntity);

    @Named("mapFailureMessagesToList")
    default List<String> mapFailureMessagesToList(String failureMessages) {
        return failureMessages == null ? null : Arrays.stream(failureMessages.split(",")).toList();
    }

}
