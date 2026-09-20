package co.mptc.ecommerce.order.persistence.adapter;

import co.mptc.ecommerce.order.domain.entity.Order;
import co.mptc.ecommerce.order.domain.port.output.OrderRepository;
import co.mptc.ecommerce.order.persistence.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    /// @param order
    @Override
    public void save(Order order) {

        //map order to orderEntity
        //map order to
    }
}
