package co.mptc.ecommerce.order.domain.port.output;

import co.mptc.ecommerce.order.domain.entity.Order;

public interface OrderRepository {

    void save(Order order);
}
