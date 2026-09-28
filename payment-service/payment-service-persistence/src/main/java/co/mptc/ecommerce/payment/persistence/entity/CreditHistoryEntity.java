package co.mptc.ecommerce.payment.persistence.entity;

import co.mptc.ecommerce.order.domain.valueObject.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "credit_histories")
public class CreditHistoryEntity {

    @Id
    private UUID id;
    private UUID customerId;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
