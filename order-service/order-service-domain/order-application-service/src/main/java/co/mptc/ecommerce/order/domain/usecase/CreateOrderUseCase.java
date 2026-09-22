package co.mptc.ecommerce.order.domain.usecase;

import co.mptc.ecommerce.order.domain.dto.CreateOrderCommand;
import co.mptc.ecommerce.order.domain.dto.CreateOrderResult;
import co.mptc.ecommerce.order.domain.entity.Business;
import co.mptc.ecommerce.order.domain.entity.Product;
import co.mptc.ecommerce.order.domain.exception.OrderDomainException;
import co.mptc.ecommerce.order.domain.port.output.BusinessRepository;
import co.mptc.ecommerce.order.domain.port.output.CustomerRepository;
import co.mptc.ecommerce.order.domain.port.output.OrderRepository;
import co.mptc.ecommerce.order.domain.valueObject.BusinessId;
import co.mptc.ecommerce.order.domain.valueObject.Money;
import co.mptc.ecommerce.order.domain.valueObject.ProductId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BusinessRepository  businessRepository;

   public CreateOrderResult execute(CreateOrderCommand createOrderCommand) {

        log.info(" executing createOrderUseCase: {}", createOrderCommand);

        //validate customer
       customerRepository.findCustomer(createOrderCommand.customerId())
               .orElseThrow(()->new OrderDomainException("Could not find customer with id: "+createOrderCommand.customerId()));

       // validate business

       List<Product> products = createOrderCommand.items().stream().map(commandOrderItem -> Product.builder()
               .id(new ProductId(commandOrderItem.productId()))
               .price(new Money(commandOrderItem.price()))
               .build()).toList();

       Business business = Business.builder()
               .id(new BusinessId(createOrderCommand.businessId()))
               .products(products)
               .build();
       business =businessRepository.findBusiness(business)
               .orElseThrow(()->new OrderDomainException("Could not find business with id: "+createOrderCommand.businessId()));

        return new CreateOrderResult(UUID.randomUUID());

    }
}
