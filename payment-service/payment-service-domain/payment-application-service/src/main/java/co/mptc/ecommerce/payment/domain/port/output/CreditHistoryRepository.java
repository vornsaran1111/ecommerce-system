package co.mptc.ecommerce.payment.domain.port.output;

import co.mptc.ecommerce.payment.domain.entity.CreditHistory;

public interface CreditHistoryRepository {
    CreditHistory save(CreditHistory creditHistory);
}
