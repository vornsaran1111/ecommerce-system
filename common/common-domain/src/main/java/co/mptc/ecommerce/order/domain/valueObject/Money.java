package co.mptc.ecommerce.order.domain.valueObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal amount) {

    //ផ្ទៀងផ្ទាត់ទឹកលុយធំជាងសូន្យ
    public boolean isGraterThenZero() {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public  static final Money  ZERO = new Money(BigDecimal.ZERO);

    //ផ្ទៀងផ្ទាត់ទឹកលុយធំជាងទឹកលុយដែលបានបញ្ចុល
    public boolean isGraterThen(Money money) {
        return amount.compareTo(money.amount) > 0;
    }

    //បន្ថែមទឹកលុយ
    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.amount)));
    }

    //ដកលុយ
    public Money subtract(Money money) {
        return new Money(setScale(this.amount.subtract(money.amount)));
    }


    //គុណលុយ
    public Money multiply(int multiplier) {
        return new Money(setScale(this.amount.multiply(BigDecimal.valueOf(multiplier))));
    }

    private BigDecimal setScale(BigDecimal inputAmount) {
        return inputAmount.setScale(2, RoundingMode.HALF_EVEN);
    }

}
