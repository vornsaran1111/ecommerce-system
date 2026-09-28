package co.mptc.ecommerce.payment.domain.port.output;

import co.mptc.ecommerce.order.domain.valueObject.CustomerId;
import co.mptc.ecommerce.payment.domain.entity.CreditEntry;

public interface CreditEntityRepository {
    CreditEntry findByCustomerId(CustomerId customerId);

    CreditEntry save(CreditEntry creditEntry);
}
