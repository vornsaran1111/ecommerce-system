package co.mptc.ecommerce.customer;

import co.mptc.ecommerce.customer.domain.service.CustomerDomainService;
import co.mptc.ecommerce.customer.domain.service.CustomerDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {

    @Bean
    public CustomerDomainService customerDomainService() {
        return new CustomerDomainServiceImpl();
    }
}
