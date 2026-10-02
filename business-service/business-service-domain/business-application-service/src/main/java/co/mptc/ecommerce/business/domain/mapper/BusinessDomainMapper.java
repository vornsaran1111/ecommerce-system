package co.mptc.ecommerce.business.domain.mapper;

import co.mptc.ecommerce.business.domain.dto.ApproveOrderCommand;
import co.mptc.ecommerce.business.domain.dto.ApproveOrderResult;
import co.mptc.ecommerce.business.domain.entity.Business;
import co.mptc.ecommerce.business.domain.entity.OrderApproval;
import co.mptc.ecommerce.business.domain.entity.OrderDetail;
import co.mptc.ecommerce.business.domain.entity.Product;
import co.mptc.ecommerce.business.domain.event.OrderApprovalEvent;
import co.mptc.ecommerce.order.domain.valueObject.BusinessId;
import co.mptc.ecommerce.order.domain.valueObject.Money;
import co.mptc.ecommerce.order.domain.valueObject.OrderId;
import co.mptc.ecommerce.order.domain.valueObject.ProductId;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessDomainMapper {
    public Business approveOrderCommandToBusiness(ApproveOrderCommand command) {
        List<Product> products = command.products().stream()
                .map(product -> Product.builder()
                        .id(new ProductId(product.productId()))
                        .quantity(product.quantity())
                        .build())
                .toList();

        OrderDetail orderDetail = OrderDetail.builder()
                .id(new OrderId(command.orderId()))
                .orderStatus(command.orderStatus())
                .totalAmount(new Money(command.totalAmount()))
                .products(products)
                .build();

        return Business.builder()
                .id(new BusinessId(command.businessId()))
                .orderDetail(orderDetail)
                .build();
    }

    public ApproveOrderResult orderApprovalEventToApproveOrderResult(OrderApprovalEvent event) {
        OrderApproval orderApproval = event.getOrderApproval();
        return new ApproveOrderResult(
                orderApproval.getId().value(),
                orderApproval.getBusinessId().value(),
                orderApproval.getOrderId().value(),
                orderApproval.getApprovalStatus(),
                event.getFailureMessages());
    }
}
