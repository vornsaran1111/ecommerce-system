package co.mptc.ecommerce.domain.port.input;

import co.mptc.ecommerce.domain.dto.CreateOrderRequest;

public interface CreateOrderUseCase {

    void execute(CreateOrderRequest request);
}
