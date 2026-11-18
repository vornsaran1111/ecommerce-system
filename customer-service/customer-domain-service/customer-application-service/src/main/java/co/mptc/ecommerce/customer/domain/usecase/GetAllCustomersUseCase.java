package co.mptc.ecommerce.customer.domain.usecase;

import co.mptc.ecommerce.core.PageResponse;
import co.mptc.ecommerce.customer.domain.dto.CustomerResult;
import co.mptc.ecommerce.customer.domain.dto.GetCustomersQuery;
import co.mptc.ecommerce.customer.domain.port.output.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllCustomersUseCase {
    private final CustomerRepository customerRepository;

    public PageResponse<CustomerResult> execute(GetCustomersQuery query) {
        log.info("Executing GetAllCustomersUseCase: {}", query);
        return customerRepository.findAll(query.page(), query.size());
    }
}
