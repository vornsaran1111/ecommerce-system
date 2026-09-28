package co.mptc.ecommerce.payment.persistence.mapper;

import co.mptc.ecommerce.payment.domain.entity.CreditEntry;
import co.mptc.ecommerce.payment.domain.entity.CreditHistory;
import co.mptc.ecommerce.payment.domain.entity.Payment;
import co.mptc.ecommerce.payment.persistence.entity.CreditEntryEntity;
import co.mptc.ecommerce.payment.persistence.entity.CreditHistoryEntity;
import co.mptc.ecommerce.payment.persistence.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentPersistenceMapper {

    // ---------- Payment ----------
    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "orderId.value", target = "orderId")
    @Mapping(source = "customerId.value", target = "customerId")
    @Mapping(source = "price.amount", target = "price")
    PaymentEntity paymentToPaymentEntity(Payment payment);

    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "orderId.value", source = "orderId")
    @Mapping(target = "customerId.value", source = "customerId")
    @Mapping(target = "price.amount", source = "price")
    Payment paymentEntityToPayment(PaymentEntity paymentEntity);

}