package co.mptc.ecommerce.domain.port.output;

import co.mptc.ecommerce.domain.entity.Order;

public interface OrderRepository {

    void save(Order order);
}
