package co.mptc.ecommerce.order.domain.usecase;

import co.mptc.ecommerce.order.domain.dto.CreateOrderCommand;
import co.mptc.ecommerce.order.domain.dto.CreateOrderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CreateOrderUseCase {

   public CreateOrderResult execute(CreateOrderCommand createOrderCommand) {

        log.info(" executing createOrderUseCase: {}", createOrderCommand);

        //validate customer
       // validate business

        return new CreateOrderResult(UUID.randomUUID());

    }
}
