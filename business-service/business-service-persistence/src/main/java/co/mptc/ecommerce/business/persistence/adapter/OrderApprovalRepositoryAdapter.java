package co.mptc.ecommerce.business.persistence.adapter;

import co.mptc.ecommerce.business.domain.entity.OrderApproval;
import co.mptc.ecommerce.business.domain.port.output.OrderApprovalRepository;
import co.mptc.ecommerce.business.persistence.entity.OrderApprovalEntity;
import co.mptc.ecommerce.business.persistence.mapper.OrderApprovalPersistenceMapper;
import co.mptc.ecommerce.business.persistence.repository.OrderApprovalJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderApprovalRepositoryAdapter implements OrderApprovalRepository {
    private final OrderApprovalJpaRepository orderApprovalJpaRepository;
    private final OrderApprovalPersistenceMapper orderApprovalPersistenceMapper;


    /// @param orderApproval
    /// @return
    @Override
    public OrderApproval save(OrderApproval orderApproval) {
        OrderApprovalEntity entity = orderApprovalPersistenceMapper.orderApprovalToOrderApprovalEntity(orderApproval);
        OrderApprovalEntity saveEntity = orderApprovalJpaRepository.save(entity);
        return orderApprovalPersistenceMapper.orderApprovalEntityToOrderApproval(saveEntity);
    }

}
