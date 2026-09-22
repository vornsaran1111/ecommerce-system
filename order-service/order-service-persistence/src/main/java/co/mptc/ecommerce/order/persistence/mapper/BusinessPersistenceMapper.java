package co.mptc.ecommerce.order.persistence.mapper;

import co.mptc.ecommerce.business.exception.BusinessPersistenceException;
import co.mptc.ecommerce.order.domain.entity.Business;
import co.mptc.ecommerce.order.domain.entity.Product;
import co.mptc.ecommerce.order.domain.valueObject.BusinessId;
import co.mptc.ecommerce.order.domain.valueObject.Money;
import co.mptc.ecommerce.order.domain.valueObject.ProductId;
import co.mptc.ecommerce.order.persistence.entity.BusinessEntity;

import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BusinessPersistenceMapper {

    default List<UUID> businessToBusinessProducts(Business business) {
        return business.getProducts().stream()
                .map(product -> product.getId().value())
                .toList();
    }

    default Business businessEntityToBusiness(List<BusinessEntity> businessEntities) {
        BusinessEntity businessEntity = businessEntities.stream()
                .findFirst()
                .orElseThrow(() -> new BusinessPersistenceException("Business could not be found"));

        List<Product> businessProducts = businessEntities.stream()
                .map(entity -> Product.builder()
                        .id(new ProductId(entity.getProductId()))
                        .name(entity.getProductName())
                        .price(new Money(entity.getProductPrice()))
                        .build())
                .toList();

        return Business.builder()
                .id(new BusinessId(businessEntity.getBusinessId()))
                .products(businessProducts)
                .active(businessEntity.getBusinessActive())
                .build();
    }
}
