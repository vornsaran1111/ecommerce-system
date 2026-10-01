package co.mptc.ecommerce.order.domain.valueObject;

public record Email(String value) {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)+$";

    // តើ email ត្រឹមត្រូវដែរឬទេ?
    public boolean isValid() {
        return value != null && value.matches(EMAIL_PATTERN);
    }
}
