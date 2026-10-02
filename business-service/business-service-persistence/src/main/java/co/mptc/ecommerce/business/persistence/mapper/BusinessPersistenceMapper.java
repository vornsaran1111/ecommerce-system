package co.mptc.ecommerce.business.persistence.mapper;

import co.mptc.ecommerce.business.domain.entity.Business;
import co.mptc.ecommerce.business.domain.entity.OrderDetail;
import co.mptc.ecommerce.business.domain.entity.Product;
import co.mptc.ecommerce.business.persistence.entity.BusinessEntity;
import co.mptc.ecommerce.business.persistence.entity.ProductEntity;
import co.mptc.ecommerce.order.domain.valueObject.BusinessId;
import co.mptc.ecommerce.order.domain.valueObject.Money;
import co.mptc.ecommerce.order.domain.valueObject.ProductId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BusinessPersistenceMapper {

    public List<UUID> businessToProductIds(Business business) {
        return business.getOrderDetail().getProducts().stream()
                .map(product -> product.getId().value())
                .toList();
    }

    public Business businessEntityToBusiness(BusinessEntity businessEntity, List<ProductEntity> productEntities) {
        List<Product> products = productEntities.stream()
                .map(productEntity -> Product.builder()
                        .id(new ProductId(productEntity.getId()))
                        .name(productEntity.getName())
                        .price(new Money(productEntity.getPrice()))
                        .available(Boolean.TRUE.equals(productEntity.getAvailable()))
                        .build())
                .toList();

        return Business.builder()
                .id(new BusinessId(businessEntity.getId()))
                .active(Boolean.TRUE.equals(businessEntity.getActive()))
                .orderDetail(OrderDetail.builder()
                        .products(products)
                        .build())
                .build();
    }
}
