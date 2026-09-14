package co.mptc.ecommerce.order.domain.port.input;

import co.mptc.ecommerce.order.domain.dto.CreateOrderRequest;

public interface CreateOrderUseCase {

    void execute(CreateOrderRequest request);
}
