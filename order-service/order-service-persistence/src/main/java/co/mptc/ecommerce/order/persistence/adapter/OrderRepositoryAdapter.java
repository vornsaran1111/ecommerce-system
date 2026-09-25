package co.mptc.ecommerce.order.persistence.adapter;

import co.mptc.ecommerce.order.domain.entity.Order;
import co.mptc.ecommerce.order.domain.port.output.OrderRepository;
import co.mptc.ecommerce.order.persistence.entity.OrderEntity;
import co.mptc.ecommerce.order.persistence.mapper.OrderPersistenceMapper;
import co.mptc.ecommerce.order.persistence.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;

    /// @param order
    @Override
    public Order saveOrder(Order order) {


        //map order to orderEntity
        OrderEntity orderEntity = orderPersistenceMapper.orderToOrderEntity(order);
        //map order to

        orderEntity.getOrderAddress().setOrder(orderEntity);

        orderEntity.getItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));


        OrderEntity saveOrderEntity = orderJpaRepository.save(orderEntity);
        return orderPersistenceMapper.orderEntityToOrder(saveOrderEntity);
    }
}
