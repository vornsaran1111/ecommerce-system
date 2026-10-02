package co.mptc.ecommerce.business.domain.port.output;

import co.mptc.ecommerce.business.domain.entity.OrderApproval;

public interface OrderApprovalRepository {
    OrderApproval save(OrderApproval orderApproval);
}
