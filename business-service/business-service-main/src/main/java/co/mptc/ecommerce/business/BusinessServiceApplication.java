package co.mptc.ecommerce.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"co.mptc.ecommerce.business.persistence"})
@EnableJpaRepositories(basePackages = {"co.mptc.ecommerce.business.persistence"})
@SpringBootApplication
@EnableDiscoveryClient
public class BusinessServiceApplication {
    static void main(String[] args) {
        SpringApplication.run(BusinessServiceApplication.class, args);
    }
}
