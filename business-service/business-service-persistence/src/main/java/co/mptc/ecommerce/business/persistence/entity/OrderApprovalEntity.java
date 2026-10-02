package co.mptc.ecommerce.business.persistence.entity;

import co.mptc.ecommerce.order.domain.valueObject.OrderApprovalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_approvals")
public class OrderApprovalEntity {

    @Id
    private UUID id;
    private UUID businessId;
    private UUID orderId;

    @Enumerated(EnumType.STRING)
    private OrderApprovalStatus approvalStatus;
}
