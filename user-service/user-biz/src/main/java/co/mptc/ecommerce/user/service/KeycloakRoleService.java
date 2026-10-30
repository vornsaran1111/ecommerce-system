package co.mptc.ecommerce.user.service;

public interface KeycloakRoleService {
    void assignRoles(String userId, String roleNameWithDelimiter);
}
