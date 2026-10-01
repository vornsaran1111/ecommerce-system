package co.mptc.ecommerce.order.domain.valueObject;

public record PhoneNumber(String number) {
    private static final String PHONE_PATTERN = "^\\+?[0-9]{8,15}$";

    // តើលេខទូរស័ព្ទត្រឹមត្រូវដែរឬទេ?
    public boolean isValid() {
        return number != null && number.matches(PHONE_PATTERN);
    }
}
