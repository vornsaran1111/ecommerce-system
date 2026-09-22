package co.mptc.ecommerce.order.persistence.entity;

import co.mptc.ecommerce.order.domain.valueObject.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID customerId;
    private UUID businessId;

    @OneToOne
    private OrderAddressEntity orderAddress;

    private BigDecimal price;

    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> orderItems;

    private UUID trackingId;
    private OrderStatus orderStatus;
    private String failureMessages; //message1 ; message2

}
