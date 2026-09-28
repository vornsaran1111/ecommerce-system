package co.mptc.ecommerce.payment.domain.usecase;

import co.mptc.ecommerce.payment.domain.dto.CreatePaymentCommand;
import co.mptc.ecommerce.payment.domain.dto.CreatePaymentResult;
import co.mptc.ecommerce.payment.domain.entity.CreditEntry;
import co.mptc.ecommerce.payment.domain.entity.CreditHistory;
import co.mptc.ecommerce.payment.domain.entity.Payment;
import co.mptc.ecommerce.payment.domain.exception.PaymentDomainException;
import co.mptc.ecommerce.payment.domain.mapper.PaymentDomainMapper;
import co.mptc.ecommerce.payment.domain.port.output.CreditEntityRepository;
import co.mptc.ecommerce.payment.domain.port.output.PaymentRepository;
import co.mptc.ecommerce.payment.domain.service.PaymentDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreatePaymentUseCase {

    private final PaymentDomainService paymentDomainService;
    private final PaymentRepository paymentRepository;
    private final PaymentDomainMapper paymentDomainMapper;
    private final CreditEntityRepository creditEntityRepository;


    public CreatePaymentResult execute(CreatePaymentCommand createPaymentCommand) {
        //convert input object by map-struct
        Payment payment = paymentDomainMapper.createPaymentCommandToPayment(createPaymentCommand);
        paymentDomainService.validateAndInitiatePayment(payment);

        //2. load customer credit
        CreditEntry creditEntry = creditEntityRepository.findByCustomerId(payment.getCustomerId());
        if (creditEntry == null) {
            throw new PaymentDomainException("Could not find credit entry for customer: "
                    + payment.getCustomerId().value());
        }

        //3. domain logic (validate → initialize → subtract credit → COMPLETED)
        CreditHistory creditHistory = paymentDomainService.validateAndInitiatePayment(payment, creditEntry);

        //save
        Payment savePayment = paymentRepository.savePayment(payment);
        if (savePayment == null) {
            throw new PaymentDomainException("Could not save payment into Database");
        }

        return new CreatePaymentResult(savePayment.getId().value(), savePayment.getPaymentStatus());
    }
}
