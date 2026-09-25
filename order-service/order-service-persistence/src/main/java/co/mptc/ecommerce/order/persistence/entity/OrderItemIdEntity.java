package co.mptc.ecommerce.order.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemIdEntity {
    private Integer id;
    private OrderEntity order;
}
