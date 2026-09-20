package co.mptc.ecommerce.order.restapi.controller;

import co.mptc.ecommerce.order.domain.dto.CreateOrderCommand;
import co.mptc.ecommerce.order.domain.dto.CreateOrderResult;
import co.mptc.ecommerce.order.domain.usecase.CreateOrderUseCase;
import co.mptc.ecommerce.order.restapi.dto.OrderCreateRequest;
import co.mptc.ecommerce.order.restapi.dto.OrderCreateResponse;
import co.mptc.ecommerce.order.restapi.mapper.OrderWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderCommandController {

    private final CreateOrderUseCase createOrderUseCase;
    private final OrderWebMapper orderWebMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderCreateResponse createOrder(@Valid @RequestBody OrderCreateRequest orderCreateRequest) {

        //Mapping logic
        CreateOrderCommand createOrderCommand =
                orderWebMapper.orderCreateRequestToCreateOrderCommand(orderCreateRequest);
        createOrderUseCase.execute(createOrderCommand);

        //usecase login
        CreateOrderResult createOrderResult = createOrderUseCase.execute(createOrderCommand);

        //Mapping login
        return orderWebMapper.createOrderResultToOrderCreateResponse(createOrderResult);
    }

}
