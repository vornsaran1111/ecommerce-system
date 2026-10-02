package co.mptc.ecommerce.business.domain.usecase;

import co.mptc.ecommerce.business.domain.dto.ApproveOrderCommand;
import co.mptc.ecommerce.business.domain.dto.ApproveOrderResult;
import co.mptc.ecommerce.business.domain.entity.Business;
import co.mptc.ecommerce.business.domain.event.OrderApprovalEvent;
import co.mptc.ecommerce.business.domain.exception.BusinessDomainException;
import co.mptc.ecommerce.business.domain.mapper.BusinessDomainMapper;
import co.mptc.ecommerce.business.domain.port.output.BusinessRepository;
import co.mptc.ecommerce.business.domain.port.output.OrderApprovalRepository;
import co.mptc.ecommerce.business.domain.service.BusinessDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApproveOrderUseCase {
    private final BusinessDomainService businessDomainService;
    private final BusinessDomainMapper businessDomainMapper;
    private final BusinessRepository businessRepository;
    private final OrderApprovalRepository orderApprovalRepository;

    public ApproveOrderResult execute(ApproveOrderCommand command) {

        log.info("executing ApproveOrderUseCase: {}", command);

        //1. convert input object
        Business business = businessDomainMapper.approveOrderCommandToBusiness(command);

        //2. load real business + product information from database
        Business businessInformation = businessRepository.findBusinessInformation(business)
                .orElseThrow(() -> new BusinessDomainException(
                        "Could not find business with id: " + command.businessId()));

        //3. put the real information into the business we validate
        business.setActive(businessInformation.isActive());
        business.getOrderDetail().getProducts().forEach(product ->
                businessInformation.getOrderDetail().getProducts().stream()
                        .filter(confirmedProduct -> confirmedProduct.getId().equals(product.getId()))
                        .findFirst()
                        .ifPresent(confirmedProduct -> product.updateWithConfirmedNamePriceAndAvailability(
                                confirmedProduct.getName(),
                                confirmedProduct.getPrice(),
                                confirmedProduct.isAvailable())));

        //4. domain logic (validate → APPROVED or REJECTED)
        List<String> failureMessages = new ArrayList<>();
        OrderApprovalEvent orderApprovalEvent = businessDomainService.validateOrder(business, failureMessages);

        //5. save approval (APPROVED and REJECTED are both saved)
        orderApprovalRepository.save(business.getOrderApproval());

        log.info("Order {} is {}", command.orderId(), business.getOrderApproval().getApprovalStatus());

        return businessDomainMapper.orderApprovalEventToApproveOrderResult(orderApprovalEvent);
    }
}
