package co.mptc.ecommerce.order.persistence.adapter;

import co.mptc.ecommerce.domain.entity.Order;
import co.mptc.ecommerce.domain.port.output.OrderRepository;
import co.mptc.ecommerce.order.persistence.repository.OrderJpaRepository;

public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    /// @param order
    @Override
    public void save(Order order) {

        //map order to orderEntity
        //map order to
    }
}
