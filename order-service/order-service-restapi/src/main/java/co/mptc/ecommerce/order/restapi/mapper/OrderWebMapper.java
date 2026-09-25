package co.mptc.ecommerce.order.restapi.mapper;

import co.mptc.ecommerce.order.domain.dto.CreateOrderCommand;
import co.mptc.ecommerce.order.domain.dto.CreateOrderResult;
import co.mptc.ecommerce.order.restapi.dto.OrderCreateRequest;
import co.mptc.ecommerce.order.restapi.dto.OrderCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {

    @Mapping(source = "orderAddress", target = "deliveryAddress")
    CreateOrderCommand orderCreateRequestToCreateOrderCommand(OrderCreateRequest orderCreateRequest);

    OrderCreateResponse createOrderResultToOrderCreateResponse(CreateOrderResult createOrderResult);
}
