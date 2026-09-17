package co.mptc.ecommerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//communication with order service persistence to get with database
@EntityScan(basePackages = {"co.mptc.ecommerce.order.persistence"
})

@EnableJpaRepositories(basePackages = {"co.mptc.ecommerce.order.persistence"
})

@SpringBootApplication
public class OrderServiceApplication {
     static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
