package co.mptc.ecommerce.customer.domain.entity;

import co.mptc.ecommerce.customer.domain.exception.CustomerDomainException;
import co.mptc.ecommerce.order.domain.entity.AggregateRoot;
import co.mptc.ecommerce.order.domain.valueObject.*;

import java.util.UUID;

public class Customer extends AggregateRoot<CustomerId> {
    private final String username;
    private String familyName;
    private String givenName;
    private Email email;
    private PhoneNumber phoneNumber;
    private LoyaltyTier loyaltyTier;
    private CustomerStatus status;

    private Customer(Builder builder) {
        super.setId(builder.id);
        username = builder.username;
        familyName = builder.familyName;
        givenName = builder.givenName;
        email = builder.email;
        phoneNumber = builder.phoneNumber;
        loyaltyTier = builder.loyaltyTier;
        status = builder.status;
    }

    // ============== start critical business logic ===========//

    public void validateCustomer() {
        validateRequiredText(username, "Username");
        validateRequiredText(familyName, "Family name");
        validateRequiredText(givenName, "Given name");
        validateEmail();
        validatePhoneNumber();
    }

    // new customer: generate id, start with BRONZE and ACTIVE
    public void initiateCustomer() {
        if (super.getId() != null || status != null) {
            throw new CustomerDomainException("Customer is not in correct state for initialization");
        }
        setId(new CustomerId(UUID.randomUUID()));
        loyaltyTier = LoyaltyTier.BRONZE;
        status = CustomerStatus.ACTIVE;
    }

    // username cannot be changed
    public void updateCustomer(String familyName, String givenName, Email email, PhoneNumber phoneNumber) {
        if (status != CustomerStatus.ACTIVE) {
            throw new CustomerDomainException("Customer is not ACTIVE, cannot update");
        }
        this.familyName = familyName;
        this.givenName = givenName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        validateCustomer();
    }

    public void deactivateCustomer() {
        if (status != CustomerStatus.ACTIVE) {
            throw new CustomerDomainException("Customer is not ACTIVE, cannot deactivate");
        }
        status = CustomerStatus.INACTIVE;
    }

    private void validateRequiredText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new CustomerDomainException(fieldName + " must not be blank");
        }
    }

    private void validateEmail() {
        if (email == null || !email.isValid()) {
            throw new CustomerDomainException("Email is not valid");
        }
    }

    private void validatePhoneNumber() {
        if (phoneNumber == null || !phoneNumber.isValid()) {
            throw new CustomerDomainException("Phone number is not valid");
        }
    }

    // ============== end critical business logic ===========//

    public String getUsername() {
        return username;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public Email getEmail() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public LoyaltyTier getLoyaltyTier() {
        return loyaltyTier;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private CustomerId id;
        private String username;
        private String familyName;
        private String givenName;
        private Email email;
        private PhoneNumber phoneNumber;
        private LoyaltyTier loyaltyTier;
        private CustomerStatus status;

        private Builder() {
        }


        public Builder id(CustomerId val) {
            id = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder familyName(String val) {
            familyName = val;
            return this;
        }

        public Builder givenName(String val) {
            givenName = val;
            return this;
        }

        public Builder email(Email val) {
            email = val;
            return this;
        }

        public Builder phoneNumber(PhoneNumber val) {
            phoneNumber = val;
            return this;
        }

        public Builder loyaltyTier(LoyaltyTier val) {
            loyaltyTier = val;
            return this;
        }

        public Builder status(CustomerStatus val) {
            status = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
