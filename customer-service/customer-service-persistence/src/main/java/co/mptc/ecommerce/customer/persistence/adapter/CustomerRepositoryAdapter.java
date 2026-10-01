package co.mptc.ecommerce.customer.persistence.adapter;

import co.mptc.ecommerce.customer.domain.entity.Customer;
import co.mptc.ecommerce.customer.domain.port.output.CustomerRepository;
import co.mptc.ecommerce.order.domain.valueObject.CustomerId;
import co.mptc.ecommerce.order.domain.valueObject.Email;
import co.mptc.ecommerce.customer.persistence.entity.CustomerEntity;
import co.mptc.ecommerce.customer.persistence.mapper.CustomerPersistenceMapper;
import co.mptc.ecommerce.customer.persistence.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerPersistenceMapper customerPersistenceMapper;
    /// @param customer
    /// @return
    @Override
    public Customer saveCustomer(Customer customer) {
        CustomerEntity customerEntity = customerPersistenceMapper.customerToCustomerEntity(customer);
        // same id already in DB → JPA does UPDATE (not INSERT)
        CustomerEntity saveCustomerEntity = customerJpaRepository.save(customerEntity);
        return customerPersistenceMapper.customerEntityToCustomer(saveCustomerEntity);
    }

    /// @param customerId
    /// @return
    @Override
    public Optional<Customer> findCustomer(CustomerId customerId) {
        return customerJpaRepository.findById(customerId.value())
                .map(customerPersistenceMapper::customerEntityToCustomer);
    }

    /// @param username
    /// @return
    @Override
    public boolean existsByUsername(String username) {
       return customerJpaRepository.existsByUsername(username);
    }

    /// @param email
    /// @return
    @Override
    public boolean existsByEmail(Email email) {
       return  customerJpaRepository.existsByEmail(email.value());
    }
}
