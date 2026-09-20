package co.mptc.ecommerce.order.domain.port.input;

import co.mptc.ecommerce.order.domain.dto.CreateOrderCommand;

public interface ExplicitPort {

    void execute(CreateOrderCommand request);
}
