package co.mptc.ecommerce.customer.persistence.adapter;

import co.mptc.ecommerce.core.PageResponse;
import co.mptc.ecommerce.customer.domain.dto.CustomerResult;
import co.mptc.ecommerce.customer.domain.entity.Customer;
import co.mptc.ecommerce.customer.domain.port.output.CustomerRepository;
import co.mptc.ecommerce.order.domain.valueObject.CustomerId;
import co.mptc.ecommerce.order.domain.valueObject.Email;
import co.mptc.ecommerce.customer.persistence.entity.CustomerEntity;
import co.mptc.ecommerce.customer.persistence.mapper.CustomerPersistenceMapper;
import co.mptc.ecommerce.customer.persistence.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerPersistenceMapper customerPersistenceMapper;

    /// @param page
    /// @param size
    /// @return
    @Override
    public PageResponse<CustomerResult> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerEntity> customerEntityPage = customerJpaRepository.findAll(pageable);
        Page<CustomerResult> customerResultPage = customerEntityPage.map(customerPersistenceMapper::toCustomerResult);
        return PageResponse.<CustomerResult>builder()
                .items(customerResultPage.getContent())
                .page(customerResultPage.getNumber())
                .size(customerResultPage.getSize())
                .totalPages(customerResultPage.getTotalPages())
                .totalElements(customerResultPage.getTotalElements())
                .hasNext(customerResultPage.hasNext())
                .build();
    }

    /// @param customer
    /// @return
    @Override
    public Customer save(Customer customer) {
        CustomerEntity savedEntity = customerJpaRepository.save(
                customerPersistenceMapper.customerToCustomerEntity(customer));
        return customerPersistenceMapper.customerEntityToCustomer(savedEntity);
    }

    /// @param customerId
    /// @return
    @Override
    public Optional<Customer> findById(CustomerId customerId) {
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
    public boolean existsByEmail(String email) {
        return customerJpaRepository.existsByEmail(email);
    }
}
