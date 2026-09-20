package co.mptc.ecommerce.order.persistence.adapter;

import co.mptc.ecommerce.order.domain.entity.Customer;
import co.mptc.ecommerce.order.domain.port.output.CustomerRepository;
import co.mptc.ecommerce.order.persistence.mapper.OrderPersistenceMapper;
import co.mptc.ecommerce.order.persistence.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {

    private  final CustomerJpaRepository customerJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;

    /// @param customerId
    /// @return
    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
        return customerJpaRepository.findById(customerId)
                .map(orderPersistenceMapper::customerEntityToCustomer);
    }
}
