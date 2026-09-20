package co.mptc.ecommerce.order.restapi.mapper;

import co.mptc.ecommerce.order.domain.dto.CreateOrderCommand;
import co.mptc.ecommerce.order.domain.dto.CreateOrderResult;
import co.mptc.ecommerce.order.restapi.dto.OrderCreateRequest;
import co.mptc.ecommerce.order.restapi.dto.OrderCreateResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {

    CreateOrderCommand orderCreateRequestToCreateOrderCommand(OrderCreateRequest orderCreateRequest);

    OrderCreateResponse createOrderResultToOrderCreateResponse(CreateOrderResult createOrderResult);
}
